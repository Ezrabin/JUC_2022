package com.bilibili.juc.practise.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureCombineDemo {

    public static void main(String[] args) {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t ---f1启动");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t ---f2启动");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        });

        CompletableFuture result = f1.thenCombine(f2, (x, y) -> {
            System.out.println("-----开始两个结果合并");
            return x + y;
        });
        System.out.println(result.join());
    }

}
