package com.jason.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenjieaj
 * @date 2022/7/14 16:36:43
 * @description
 */
public class Test3 {
    private static final ThreadLocal<String> TMP = new ThreadLocal<>();
    private static final Map<String, String> MAP = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        TMP.set("main");
        MAP.put("k1", "v1");
        print();
        new Thread(()->{
            print();
            TMP.set("thread");
            print();
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print();
    }

    public static void print() {
        System.out.println(Thread.currentThread().getName() + "--tmp:" + TMP.get());
        System.out.println(Thread.currentThread().getName() + "--map:" + MAP.toString());
        System.out.println("----------------------------");
    }

}
