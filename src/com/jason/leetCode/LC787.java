package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class LC787 {
    /**
     * @param n       一共n个点
     * @param flights 所有的边
     * @param src     起始点
     * @param dst     目标点
     * @param k       最多中转点
     * @return
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        //map[i][j] > 0 联通，值为价格，==0表示么有边
//        int[][] map = new int[n][n];
//        for (int[] flight : flights) {
//            map[flight[0]][flight[1]] = flight[3];
//        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            Map<Integer, Integer> edges = map.getOrDefault(flight[0], new HashMap<>());
            edges.put(flight[1], flight[2]);
            map.put(flight[0], edges);
        }
        //是否已访问过
        boolean[] visited = new boolean[n];
        visited[src] = true;
        Map<Integer, Info> cache = new HashMap<>();
        return process(map, visited, src, dst, k, cache).cost;
    }

    class Info {
        public int step;
        public int cost;

        public Info(int step, int cost) {
            this.step = step;
            this.cost = cost;
        }
    }

    private Info process(/*int[][] map*/ Map<Integer, Map<Integer, Integer>> map, boolean[] visited, int cur, int dst, int rest, Map<Integer, Info> cache) {
        //已到达终点
        if (cur == dst) {
            return new Info(0, 0);
        }
        //次数用尽
        if (rest == 0) {
            return new Info(0, -1);
        }
        if (cache.containsKey(cur)) {
            return cache.get(cur);
        }
        Map<Integer, Integer> curEdge = map.get(cur);
        //没路了
        if (curEdge == null) {
            Info info = new Info(1, -1);
            cache.put(cur, info);
            return info;
        }
        Info res = new Info(-1, -1);
        for (Integer target : curEdge.keySet()) {
            visited[target] = true;
            Info info = process(map, visited, target, dst, rest - 1, cache);
            if (info.cost > 0) {
                res = (res.cost == -1 || (info.cost + curEdge.get(target) < rest)) ?
                        new Info(info.step + 1, info.cost + curEdge.get(target)) : res;
            }
            visited[target] = false;
        }
        if (res.cost > 0) {
            cache.put(cur, res);
        }
        return res;
    }


    //TODO 按步数做dp
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //dp[i][j]:从src出发，走i步，到达j的最小花费【中间节点最多为k个，走n步会产生n-1个中间节点】
        int[][] dp = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for (int i = 1; i < k + 2; i++) {
            for (int[] flight : flights) {
                int source = flight[0], target = flight[1], cost = flight[2];
                if (dp[i - 1][source] != Integer.MAX_VALUE) {
                    dp[i][target] = Math.min(dp[i][target], dp[i - 1][source] + cost);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k + 2; i++) {
            ans = Math.min(ans, dp[i][dst]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
