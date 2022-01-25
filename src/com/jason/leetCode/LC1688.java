package com.jason.leetCode;

public class LC1688 {
    public static int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            ans += n >> 1;
            n = (n & 1) + n >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(numberOfMatches(7));
    }
}
