package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/2/12 22:35:06
 * @description
 */
public class LC2335 {
    public int fillCups(int[] amount) {
//        Arrays.sort(amount);
//        if (amount[0] + amount[1] <= amount[2]) {
//            return amount[2];
//        } else {
//            return (amount[0] + amount[1] + amount[2] + 1) >> 1;
//        }

        int sum = 0, max = 0;
        for (int num : amount) {
            max = Math.max(max, num);
            sum += num;
        }
        return sum >= max << 1 ? ((sum + 1) >> 1) : max;
    }
}
