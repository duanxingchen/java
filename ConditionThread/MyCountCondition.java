package com.example.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyCountCondition {
    private String oid;                        //账号
    private int cash;                            //账户余额
    private Lock lock =new ReentrantLock();                //账户锁
    private Condition _save = lock.newCondition();    //存款条件
    private Condition _draw = lock.newCondition();    //取款条件
    MyCountCondition(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public  void saving(int x, String name) {
        lock.lock();                        //获取锁
        if (x > 1000) {
            try {
                System.out.println(Thread.currentThread().getName() + "人的排队");
                _save.await();
                System.out.println(Thread.currentThread().getName() + " _save.await();被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cash += x;                    //存款
            System.out.println(Thread.currentThread().getName() + "存款" + x +"，当前余额为" + cash);
        }
        _draw.signalAll();            //唤醒所有等待线程。
        lock.unlock();                    //释放锁
    }

    /**
     * 取款
     *
     * @param x        操作金额
     * @param name 操作人
     */
    public  void drawing(int x, String name) {
        lock.lock();                        //获取锁
        if (cash - x < 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "在等待存钱");
                _draw.await();             //阻塞取款操作
                System.out.println(Thread.currentThread().getName() + "_draw.await()被唤醒");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else {
            cash -= x;                     //取款
            System.out.println(Thread.currentThread().getName() + "取款" + x +"，当前余额为" + cash);
        }
        _save.signalAll();             //唤醒所有存款操作
        lock.unlock();                    //释放锁
    }
}