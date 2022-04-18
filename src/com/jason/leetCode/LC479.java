package com.jason.leetCode;

public class LC479 {
    public static int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int top = (int) Math.pow(10, n) - 1;
        int ans = 0;
        // 枚举回文
        retry:
        for (int left = top; left > 0; left--) {
            long cur = left;
            for (int x = left; x > 0; x /= 10) {
                cur = cur * 10 + x % 10;
            }
            for (long i = top; i * i > cur; i--) {
                if (cur % i == 0) {
                    ans = (int) (cur % 1337);
                    break retry;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(largestPalindrome(2));
    }
}
