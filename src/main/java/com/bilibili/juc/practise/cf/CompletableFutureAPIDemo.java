package com.bilibili.juc.practise.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        //System.out.println("completableFuture = " + completableFuture.get(2L, TimeUnit.SECONDS));
        //System.out.println("completableFuture = " + completableFuture.join());
        TimeUnit.SECONDS.sleep(2);
        //System.out.println("completableFuture = " + completableFuture.getNow("还未计算完成"));
        //System.out.println("completableFuture = " + completableFuture.complete("completeValue") + "\t" + completableFuture.get());
    }
}
