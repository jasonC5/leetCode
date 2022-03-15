package com.jason.leetCode;

public class LC67 {
    public static String addBinary(String a, String b) {
        int carry = 0;
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(charsA.length, charsB.length); i++) {
            if (i < charsA.length) {
                carry += charsA[charsA.length - i - 1] - '0';
            }
            if (i < charsB.length) {
                carry += charsB[charsB.length - i - 1] - '0';
            }
            sb.append(carry & 1);
            carry >>= 1;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));
    }
}
