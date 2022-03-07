package com.jason.leetCode;

public class LC504 {
    public static String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        boolean negative = num < 0;
        num = Math.abs(num);
        while (num > 0) {
            int n = num % 7;
            sb.append(n);
            num /= 7;
        }
        if (negative) {
            sb.append("-");
        }
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(-7));
    }
}
