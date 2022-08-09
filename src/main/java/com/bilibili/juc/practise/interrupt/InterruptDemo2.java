package com.bilibili.juc.practise.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {

                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted被修改为true，程序停止");
                    break;
                }
                System.out.println("t1 -----hello Interrupt");
            }
        }, "t1");
        t1.start();

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t1.interrupt();

            System.out.println("t2 -----hello Interrupt");

        }, "t2").start();

    }
}
