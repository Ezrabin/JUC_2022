package com.bilibili.juc.practise.cf;

import java.util.concurrent.*;

public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture f1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + "\t -----come in");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 over");
        }, threadPool);

        System.out.println("f1.get() = " + f1.get());


        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                int i = 1 / 0;
                System.out.println(Thread.currentThread().getName() + "\t -----come in");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        }, threadPool).whenComplete((s, throwable) -> {
            System.out.println("whenCompletef2.get() = " + s);
        }).exceptionally(throwable -> {
            System.out.println("异常：" + throwable.getMessage() + throwable.getCause());
            return null;
        });

        //System.out.println("f2.get() = " + f2.get());
        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");


        threadPool.shutdown();
    }
}
