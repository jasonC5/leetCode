package com.jason.leetCode;

public class LC507 {
    public static boolean checkPerfectNumber(int num) {
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            sum += num % i == 0 ? i : 0;
        }
        return num == sum;
    }

    public static void main(String[] args) {
        boolean b = checkPerfectNumber(28);
        System.out.println(b);
    }

    public static boolean checkPerfectNumber2(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i < num) {
                    sum += num / i;
                }
            }
        }
        return num == sum;
    }
}
