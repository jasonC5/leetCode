package com.jason.leetCode;

public class LC007 {
    public static void main(String[] args) {
        System.out.println(Solution.reverse(1534236469));
    }
    static class Solution {
        public static int reverse(int x) {
            int y = 0;
            while(x != 0){
                if (y < Integer.MIN_VALUE / 10 || y > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int i = x % 10;
                y = y*10 + i;
                x /= 10;
            }
            return y;
        }
    }
}
