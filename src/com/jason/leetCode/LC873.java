package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/7/9 11:51:20
 * @description
 */
public class LC873 {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int[][] dp = new int[n][n];
        Map<Integer, Integer> valueIdxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueIdxMap.put(arr[i], i);
        }
        for (int i = 2; i < n; i++) {
            for (int j = i - 1; j > 0 && arr[j] << 1 > arr[i]; j--) {
                int find = arr[i] - arr[j];
                if (!valueIdxMap.containsKey(find)) {
                    continue;
                }
                Integer k = valueIdxMap.get(find);
                dp[j][i] = Math.max(dp[k][j] + 1, 3);
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }
}
