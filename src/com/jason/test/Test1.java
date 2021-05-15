package com.jason.test;

public class Test1 {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            String code = String.valueOf((int) ((Math.random()*9+1)*Math.pow(10,5)));//提高效率：
            System.out.println(code);
        }
    }
}
