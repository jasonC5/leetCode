package com.jason.leetCode;

public class LC1672 {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            int sum = 0;
            for (int num : account) {
                sum += num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
