package com.jason.msjd;

/**
 * @author chenjieaj
 * @date 2022/9/26 9:02:11
 * @description
 */
public class MS_17_19 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length, xor = 0, b = 0;
        int[] ans = new int[2];
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n + 2; i++) {
            xor ^= i;
        }
        //找出异或和最右边的1，说明这个二进制位上面，两个数字不一样
        b = xor == Integer.MIN_VALUE ? xor : xor & -xor;
        for (int num : nums) {
            if ((num & b) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        for (int i = 1; i <= n + 2; i++) {
            if ((i & b) == 0) {
                ans[0] ^= i;
            } else {
                ans[1] ^= i;
            }
        }
        return ans;
    }
}
