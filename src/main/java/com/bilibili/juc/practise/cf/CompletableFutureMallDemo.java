package com.bilibili.juc.practise.cf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("tmall")
    );

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall -> String.format(productName + "in%s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName)))
                .collect(Collectors.toList());

    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + "in%s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream().map(s -> s.join()).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        List<String> mysql = getPrice(list, "mysql");
        for (int i = 0; i < mysql.size(); i++) {
            System.out.println(mysql.get(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("----costTime: " + (end - startTime) + " 毫秒");
        System.out.println("----------------------------------------------------");
        long startTime2 = System.currentTimeMillis();
        List<String> mysql2 = getPriceByCompletableFuture(list, "mysql");
        for (int i = 0; i < mysql2.size(); i++) {
            System.out.println(mysql2.get(i));
        }

        long end2 = System.currentTimeMillis();
        System.out.println("----costTime: " + (end2 - startTime2) + " 毫秒");
    }

}


class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }


}
