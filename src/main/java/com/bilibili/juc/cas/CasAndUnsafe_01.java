package com.bilibili.juc.cas;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CasAndUnsafe_01 {
    //private static int m = 0;
    //原子类型的操作
    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {

                for (int j = 0; j < 10000; j++) {
                    m.incrementAndGet();
                }
                latch.countDown();

            });
        }
        Arrays.stream(threads).forEach((t) -> t.start());
        latch.await();
        System.out.println(m);
    }


}
