package com.jason.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2023/3/15 9:16:54
 * @description
 */
public class LC1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, Set<Integer>> lineMap = new HashMap<>();
        for (int[] road : roads) {
            int i = road[0];
            int j = road[1];
            Set<Integer> set1 = lineMap.getOrDefault(i, new HashSet<>());
            Set<Integer> set2 = lineMap.getOrDefault(j, new HashSet<>());
            set1.add(j);
            set2.add(i);
            lineMap.put(i, set1);
            lineMap.put(j, set2);
        }
        Set<Integer> set1, set2;
        int ans = 0, cur;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (set1 = lineMap.get(i)) != null && (set2 = lineMap.get(j)) != null) {
                    cur = set1.size() + set2.size() - (set1.contains(j) ? 1 : 0);
                    ans = Math.max(ans, cur);
                }
            }
        }
        return ans;
    }

    public int maximalNetworkRank2(int n, int[][] roads) {
        int ans = 0, cur;
        boolean[][] line = new boolean[n][n];
        int[] lineNum = new int[n];
        for (int[] road : roads) {
            int i = road[0];
            int j = road[1];
            line[i][j] = true;
            line[j][i] = true;
            lineNum[i]++;
            lineNum[j]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cur = lineNum[i] + lineNum[j] - (line[i][j] ? 1 : 0);
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
}
