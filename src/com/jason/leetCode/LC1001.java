package com.jason.leetCode;

import java.util.*;

public class LC1001 {
    public static int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Set<Long> lampSet = new HashSet<>();
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> x = new HashMap<>();
        Map<Integer, Integer> y = new HashMap<>();
        //统计每条线上灯出现的次数
        for (int[] lamp : lamps) {
            long point = ((long) lamp[0] << 32) | lamp[1];
            if (lampSet.contains(point)) {//同一个点上的灯，只记一次
                continue;
            }
            lampSet.add(point);
            Integer count = row.getOrDefault(lamp[0], 0);
            row.put(lamp[0], count + 1);
            count = col.getOrDefault(lamp[1], 0);
            col.put(lamp[1], count + 1);
            count = x.getOrDefault(lamp[0] - lamp[1], 0);
            x.put(lamp[0] - lamp[1], count + 1);
            count = y.getOrDefault(lamp[0] + lamp[1], 0);
            y.put(lamp[0] + lamp[1], count + 1);
        }
        int len = queries.length;
        int[] ans = new int[len];
        int[][] position = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < len; i++) {
            int r = queries[i][0];
            int c = queries[i][1];
            if (row.containsKey(r) || col.containsKey(c) || x.containsKey(r - c) || y.containsKey(r + c)) {
                ans[i] = 1;
            } else {
                ans[i] = 0;
            }
            //关灯
            for (int[] ints : position) {
                int ri = r + ints[0];
                int ci = c + ints[1];
                if (ri < 0 || ci < 0 || ri >= n || ci >= n) {
                    continue;
                }
                long point = ((long) ri << 32) | ci;
                if (lampSet.contains(point)) {
                    //有灯，关
                    lampSet.remove(point);
                    // 四个map上都 -1
                    Integer count = row.get(ri);
                    if (count == 1) {
                        row.remove(ri);
                    } else {
                        row.put(ri, count - 1);
                    }
                    count = col.get(ci);
                    if (count == 1) {
                        col.remove(ci);
                    } else {
                        col.put(ci, count - 1);
                    }
                    count = x.get(ri - ci);
                    if (count == 1) {
                        x.remove(ri - ci);
                    } else {
                        x.put(ri - ci, count - 1);
                    }
                    count = y.get(ri + ci);
                    if (count == 1) {
                        y.remove(ri + ci);
                    } else {
                        y.put(ri + ci, count - 1);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] lamps = {{2, 5}, {4, 2}, {0, 3}, {0, 5}, {1, 4}, {4, 2}, {3, 3}, {1, 0}};
        int[][] queries = {{4, 3}, {3, 1}, {5, 3}, {0, 5}, {4, 4}, {3, 3}};
        System.out.println(Arrays.toString(gridIllumination(n, lamps, queries)));
    }
}
