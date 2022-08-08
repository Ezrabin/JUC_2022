package com.bilibili.juc.practise.cf;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
//        CompletableFuture.supplyAsync(() -> {
//            return 1;
//        }).thenApply(f -> {
//            return f + 2;
//        }).thenApply(f -> {
//            return f + 3;
//        }).thenAccept(r -> System.out.println(r));
//    }


        CompletableFuture.supplyAsync(() -> {
            return "resultA";
        }).thenRun(() -> {
            // System.out.println("resultA");
        }).join();
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return "resultA";
        }).thenRun(() -> {
            // System.out.println("resultA");
        }).join());

        System.out.println(CompletableFuture.supplyAsync(() -> {
            return "resultA";
        }).thenAccept((r) -> {
            System.out.println(r);
        }).join());


        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply((r) -> r + "resultB").join());
    
    }
}