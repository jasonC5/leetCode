package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/6/13 10:35:22
 * @description
 */
public class LC2475 {
    public int unequalTriplets(int[] nums) {// 理解错误，理解成了多少种结果组合
        int n = nums.length;
        boolean[] exists = new boolean[n];
        for (int num : nums) {
            exists[num] = true;
        }
        int k = 0;
        for (boolean exist : exists) {
            k += exist ? 1 : 0;
        }
        return k > 2 ? k * (k - 1) * (k - 2) / 6 : 0;
    }

    public int unequalTriplets2(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : nums) {
            count.merge(x, 1, Integer::sum);
        }
        int res = 0, n = nums.length, t = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            res += t * entry.getValue() * (n - t - entry.getValue());
            t += entry.getValue();
        }
        return res;
    }

}
