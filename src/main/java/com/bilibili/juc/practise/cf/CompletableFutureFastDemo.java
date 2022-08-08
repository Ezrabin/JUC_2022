package com.bilibili.juc.practise.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureFastDemo {
    public static void main(String[] args) {

        CompletableFuture a = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("A come in");
                TimeUnit.MILLISECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playA";
        });
        CompletableFuture b = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("B come in");
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "playB";
        });
        System.out.println(a.applyToEither(b, f -> {
            return f + " is winer";
        }).join());

    }
}
