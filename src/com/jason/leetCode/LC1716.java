package com.jason.leetCode;

public class LC1716 {
    public static int totalMoney(int n) {
        int count = n / 7;
        int sum = (28 * 2 + (count - 1) * 7) * count / 2;
        int sur = n % 7;
        if (sur != 0) {
            sum += (count * 2 + (sur + 1)) * sur / 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(totalMoney(4));
        System.out.println(totalMoney(10));
        System.out.println(totalMoney(20));
        System.out.println(totalMoney(0));
    }

}
