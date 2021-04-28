package com.jason.leetCode;

public class LC633 {

    public static void main(String[] args) {
        int c = 0;
        boolean b = Solution.judgeSquareSum(c);
        System.out.println(b);
    }

    public static class Solution {
        public static boolean judgeSquareSum(int c) {
            int a = 0;
            while (a <= Math.sqrt(c)) {
                double b = Math.sqrt(c - a*a++);
                if (b == (int)b) {
                    return true;
                }
            }
            return false;
        }
    }
}
