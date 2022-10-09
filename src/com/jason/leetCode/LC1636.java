package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/9/19 9:12:00
 * @description
 */
public class LC1636 {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int n = countMap.size();
        int[][] arr = new int[n][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            arr[i][0] = entry.getKey();
            arr[i++][1] = entry.getValue();
        }
        Arrays.sort(arr, (i1, i2) -> i1[1] == i2[1] ? i2[0] - i1[0] : i1[1] - i2[1]);
        int[] ans = new int[nums.length];
        i = 0;
        for (int[] ints : arr) {
            for (int j = 0; j < ints[1]; j++) {
                ans[i++] = ints[0];
            }
        }
        return ans;
    }
}
