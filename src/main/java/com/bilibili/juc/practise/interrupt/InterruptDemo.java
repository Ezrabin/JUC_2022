package com.bilibili.juc.practise.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello volatile");
            }
        }, "t1").start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isStop = true;
            System.out.println("t2 -----hello volatile");

        }, "t1").start();

    }

}
