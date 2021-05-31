package com.jason.leetCode;

public class LC461 {

    public static void main(String[] args) {
        int a = Solution.hammingDistance(4,1);
        System.out.println(a);
    }

    static class Solution {
        public static int hammingDistance(int x, int y) {
            //异或，右移计数
            int res = x ^ y;
            int mask= 1;
            int count = 0;
            while(res != 0){
                count += res & mask;
                res >>>= 1;
            }
            return count;
        }
    }
}
