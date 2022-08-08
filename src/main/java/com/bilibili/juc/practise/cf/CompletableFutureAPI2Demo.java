package com.bilibili.juc.practise.cf;

import java.util.concurrent.*;

public class CompletableFutureAPI2Demo {
    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "子线程开始执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("11111 ");
            return 1;
        }, threadPool).handle((f, e) -> {
            System.out.println("22222");
            int i = 10 / 0;
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("33333");
            return f + 2;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----计算结果： " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        threadPool.shutdown();
        //TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "----主线程先去忙其它任务");
    }
}
