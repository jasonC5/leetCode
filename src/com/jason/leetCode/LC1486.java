package com.jason.leetCode;

public class LC1486 {

    public static void main(String[] args) {
        int res = Solution.xorOperation(5, 0);
        System.out.println(res);
    }

    static class Solution {
        public static int xorOperation(int n, int start) {
            int sum = 0;
            for(int i = 0; i < n; i++){
                sum ^= (start + 2*i);
            }
            return sum;
        }
    }
}
