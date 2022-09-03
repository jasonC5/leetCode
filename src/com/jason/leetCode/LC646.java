package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC646 {
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (i1, i2) -> i1[0] - i2[0]);
        Map<String, Integer> dp = new HashMap<>();
        return process(pairs, 0, Integer.MIN_VALUE, dp);
    }

    private int process(int[][] pairs, int idx, int pre, Map<String, Integer> dp) {
        if (idx == pairs.length) {
            return 0;
        }
        String key = idx + "_" + pre;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int p1 = process(pairs, idx + 1, pre, dp);
        int p2 = 0;
        if (pairs[idx][0] > pre) {
            p2 = 1 + process(pairs, idx + 1, pairs[idx][1], dp);
        }
        int max = Math.max(p1, p2);
        dp.put(key, max);
        return max;
    }

    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (i1, i2) -> i1[0] - i2[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
