package com.example.condition;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ConditionThreadApplication {

    public static void main(String[] args) {
        //创建并发访问的账户
        MyCountCondition myCount = new MyCountCondition("95599200901215522", 100);
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t1 = new DrawThreadCondition("张三", myCount, 2000);
        Thread t2 = new DrawThreadCondition("李四", myCount, 3600);
        Thread t3 = new DrawThreadCondition("王五", myCount, 2700);
        Thread t4 = new DrawThreadCondition("老张", myCount, 600);
        Thread t5 = new DrawThreadCondition("老牛", myCount, 1300);
        Thread t6 = new SaveThreadCondition("胖子", myCount, 8000);
        Thread t7 = new SaveThreadCondition("胖子", myCount, 800);
        //执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        //关闭线程池
        pool.shutdown();
    }
}
