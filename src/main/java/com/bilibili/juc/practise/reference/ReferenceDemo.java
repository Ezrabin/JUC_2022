package com.bilibili.juc.practise.reference;


import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("invoke method = finalize");
    }
}


public class ReferenceDemo {

    public static void main(String[] args) {
        Object o = new Object();
        MyObject myObject = new MyObject();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("myObject");
        threadLocal.get();
        threadLocal.remove();

        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        System.out.println("phantomReference = " + phantomReference);
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            try {
                for (; ; ) {
                    list.add(new byte[1024 * 1024]);
                    TimeUnit.MICROSECONDS.sleep(500);
                    System.out.println("list add ok " + phantomReference.get());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {

            for (; ; ) {
                Reference<? extends MyObject> poll = referenceQueue.poll();
                if (poll != null) {
                    System.out.println("poll = " + poll);
                    System.out.println("虚对象加入队列");
                    break;
                }
            }
        }, "t2").start();
//        System.out.println("myObject = " + myObject);
//        myObject = null;
//        System.gc();
//        System.out.println("myObject = " + myObject);
    }
}
