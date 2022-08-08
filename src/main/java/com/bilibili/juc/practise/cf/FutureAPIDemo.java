package com.bilibili.juc.practise.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "task over";
        });


        new Thread(futureTask, "t1").start();
        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                // 暂停毫秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中，不要再催了，越催越慢 ，再催熄火");
            }
        }
       //System.out.println("futureTask = " + futureTask.get());
        //System.out.println("futureTask = " + futureTask.get(3,TimeUnit.SECONDS));

    }
}
