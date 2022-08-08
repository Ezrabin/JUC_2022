package com.bilibili.juc.practise.cf;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.*;

public class CompletableFutureWithThreadPoolDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1号任务 " + Thread.currentThread().getName());
            return "abc";
        }, threadPool).thenRunAsync(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2号任务 " + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3号任务 " + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("4号任务 " + Thread.currentThread().getName());
        });

        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
    }
}
