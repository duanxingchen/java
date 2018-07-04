package com.example.syn;

class MyCount {
    private String oid;                        //账号
    private int cash;                            //账户余额

    MyCount(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public synchronized void saving(int x, String name) {
        if (x > 0) {
            cash += x;                    //存款
            System.out.println(Thread.currentThread().getName() + "存款" + x +"，当前余额为" + cash);
        }
        notifyAll();            //唤醒所有等待线程。
    }

    /**
     * 取款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public synchronized void drawing(int x, String name) {
        if (cash - x < 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "在等待存钱");
                wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else {
            cash -= x;                     //取款
            System.out.println(name + "取款" + x +"，当前余额为" + cash);
        }
        notifyAll();             //唤醒所有存款操作
    }
}