package com.jason.leetCode;

public class LC2029 {
    public boolean stoneGameIX(int[] stones) {
        int[] counts = new int[3];
        for (int stone : stones) {
            counts[stone % 3]++;
        }
        if ((counts[0] & 1) == 0) {
            return counts[1] >= 1 && counts[2] >= 1;
        }
        return Math.abs(counts[1] - counts[2]) > 2;
    }
}
