package com.jason.leetCode;

public class LC137 {

    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,1,99};
        int i = Solution.singleNumber(nums);
        System.out.println(i);
    }

    public static class Solution {
        public static int singleNumber(int[] nums) {
            //
            int ans = 0;
            //32位，按位算
            for (int i = 0; i < 32; i++) {
                long sum = 0L;
                long mask = 1 << i;
                for (int num : nums) {
                    sum += num & mask;
                }
                ans |= sum % (mask * 3);
            }
            return ans;
        }
    }
}
