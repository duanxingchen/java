package com.example.threadtest;

/**
 * 生产者
 */
class Producer extends Thread {
    private int neednum;                //生产产品的数量
    private Godown godown;            //仓库

    Producer(int neednum, Godown godown) {
        this.neednum = neednum;
        this.godown = godown;
    }

    public void run() {
        //生产指定数量的产品
        System.out.println(Thread.currentThread().getName()+"开始运行");
        godown.produce(neednum);
    }
}