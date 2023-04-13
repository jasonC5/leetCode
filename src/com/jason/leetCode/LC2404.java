package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/4/13 18:00:53
 * @description
 */
public class LC2404 {
    public int mostFrequentEven(int[] nums) {
        int ans = -1;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            if ((i & 1) == 0) {
                count.put(i, count.getOrDefault(i, 0) + 1);
            }
        }
        if (count.size() > 0) {
            int max = 0;
            for (Integer key : count.keySet()) {
                Integer num = count.get(key);
                if (num > max || (num == max && key < ans)) {
                    ans = key;
                    max = num;
                }
            }
        }
        return ans;
    }
}
