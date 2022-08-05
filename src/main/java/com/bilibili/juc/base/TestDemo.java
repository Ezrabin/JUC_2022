package com.bilibili.juc.base;

import org.omg.PortableInterceptor.ACTIVE;

import java.lang.invoke.VolatileCallSite;
import java.security.AccessControlContext;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 1;
    }
}

/**
 * @auther zzyy
 * @create 2022-01-21 12:48
 */
public class TestDemo {
    volatile int age;



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> stringFutureTask = new FutureTask<>(new MyThread());
        new Thread(stringFutureTask).start();
        System.out.println("stringFutureTask.get() = " + stringFutureTask.get());


    }
    }