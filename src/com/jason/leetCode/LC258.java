package com.jason.leetCode;

public class LC258 {
    public static int addDigits(int num) {
        while (num >= 10) {
            int cur = num, next = 0;
            while (cur > 0) {
                next += cur % 10;
                cur /= 10;
            }
            num = next;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(addDigits(15));
    }
}
