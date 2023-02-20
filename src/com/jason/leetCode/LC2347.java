package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/2/20 9:44:46
 * @description
 */
public class LC2347 {
    public String bestHand(int[] ranks, char[] suits) {
        if (suits[0] == suits[1] && suits[0] == suits[2] && suits[0] == suits[3] && suits[0] == suits[4]) {
            return "Flush";
        }
        int max = 0;
        int[] cnt = new int[14];
        for (int i : ranks) {
            max = Math.max(max, ++cnt[i]);
        }
        return max >= 3 ? "Three of a Kind" : (max == 2 ? "Pair" : "High Card");
    }
}
