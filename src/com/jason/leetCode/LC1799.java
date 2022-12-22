package com.jason.leetCode;

import java.util.Date;

/**
 * @author chenjieaj
 * @date 2022/12/22 10:14:51
 * @description
 */
public class LC1799 {

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        int[][] gcdTmp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                gcdTmp[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int all = 1 << n;
        for (int s = 1; s < all; ++s) {
            int t = Integer.bitCount(s);
            if ((t & 1) != 0) {
                continue;
            }
            for (int i = 0; i < n; ++i) {
                if (((s >> i) & 1) != 0) {
                    for (int j = i + 1; j < n; ++j) {
                        if (((s >> j) & 1) != 0) {
                            dp[s] = Math.max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + t / 2 * gcdTmp[i][j]);
                        }
                    }
                }
            }
        }
        return dp[all - 1];
    }

    public int maxScore0(int[] nums) {
        int n = nums.length;
        int all = 1 << n;
        int[] dp = new int[all];
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }
        for (int delegate = 0; delegate < all; delegate++) {
            int cnt = Integer.bitCount(delegate);
            if ((cnt & 1) == 1) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (((delegate >> i) & 1) == 0) {
                    continue;
                }
                for (int j = i + 1; j < n; j++) {
                    if (((delegate >> j) & 1) == 0) {
                        continue;
                    }
                    dp[delegate] = Math.max(dp[delegate], dp[delegate ^ (1 << i) ^ (1 << j)] + gcd[i][j] * (cnt >> 1));
                }
            }
        }
        return dp[all - 1];
    }


    public int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(1671676000522L).toString());
        System.out.println(new Date(1617759261003L).toString());
    }
}
