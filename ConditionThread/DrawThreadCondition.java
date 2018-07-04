package com.example.condition;



public class DrawThreadCondition extends Thread {
    private String name;                //操作人
    private MyCountCondition myCount;        //账户
    private int x;                            //存款金额

    DrawThreadCondition(String name, MyCountCondition myCount, int x) {
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run() {
        myCount.drawing(x, name);
    }
}
