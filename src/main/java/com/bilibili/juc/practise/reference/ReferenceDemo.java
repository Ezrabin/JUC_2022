package com.bilibili.juc.practise.reference;


class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("invoke method = finalize");
    }
}


public class ReferenceDemo {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        System.out.println("myObject = " + myObject);
        myObject = null;
        System.gc();
        System.out.println("myObject = " + myObject);
    }
}
