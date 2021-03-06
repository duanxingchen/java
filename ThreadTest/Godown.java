package com.example.threadtest;

/**
 * 仓库
 */
class Godown {
    public static final int max_size = 100;//最大库存量
    public int curnum;    //当前库存量

    Godown() {
    }

    Godown(int curnum) {
        this.curnum = curnum;
    }

    /**
     * 生产指定数量的产品
     *
     * @param neednum
     */
    public synchronized void produce(int neednum) {
        System.out.println(Thread.currentThread().getName()+"produce占有资源");

        //测试是否需要生产
        while (neednum + curnum > max_size) {
            System.out.println("要生产的产品数量" + neednum +"超过剩余库存量" + (max_size - curnum) +"，暂时不能执行生产任务!");
            try {
                //当前的生产线程等待
                System.out.println(Thread.currentThread().getName()+"在等待5s");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"produce在wait处释放资源");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //满足生产条件，则进行生产，这里简单的更改当前库存量
        curnum += neednum;
        System.out.println(Thread.currentThread().getName()+"已经生产了" + neednum +"个产品，现仓储量为" + curnum);
        //唤醒在此对象监视器上等待的所有线程
        System.out.println(Thread.currentThread().getName()+"produce释放资源");
        notifyAll();
    }

    /**
     * 消费指定数量的产品
     *
     * @param neednum
     */
    public synchronized void consume(int neednum) {
        System.out.println(Thread.currentThread().getName()+"consume占有资源");
        //测试是否可消费
        while (curnum < neednum) {

            try {
                //当前的生产线程等待
                System.out.println(Thread.currentThread().getName()+"在等待5s");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"consume在wait处释放资源");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //满足消费条件，则进行消费，这里简单的更改当前库存量
        curnum -= neednum;
        System.out.println(Thread.currentThread().getName()+"已经消费了" + neednum +"个产品，现仓储量为" + curnum);
        //唤醒在此对象监视器上等待的所有线程
        System.out.println(Thread.currentThread().getName()+"consume释放资源");
        notifyAll();
    }
}