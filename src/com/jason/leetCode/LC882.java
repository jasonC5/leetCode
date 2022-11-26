package com.jason.leetCode;

import java.util.*;

/**
 * @author JasonC5
 */
public class LC882 {
    public int reachableNodes_F(int[][] edges, int maxMoves, int n) {// 记录的思路错误
        // 点的 list
        List<int[]>[] pointList = new List[n];
        // 记录距离 map
        Map<Integer, Integer> visited = new HashMap<>();
        // BFS Queue
        Queue<int[]> queue = new LinkedList<>();
        // 把图转为map
        for (int[] edge : edges) {
            int point1 = edge[0], point2 = edge[1], len = edge[2];
            pointList[point1] = pointList[point1] == null ? new ArrayList<>() : pointList[point1];
            pointList[point2] = pointList[point2] == null ? new ArrayList<>() : pointList[point2];
            pointList[point1].add(new int[]{point2, len});
            pointList[point2].add(new int[]{point1, len});
        }
        int ans = 1;
        queue.add(new int[]{0, maxMoves});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int cur = poll[0];
                int len = poll[1];
                List<int[]> nexts = pointList[cur];
                for (int[] next : nexts) {
                    ans += visited.containsKey(next[0]) ? 0 : 1;
                    len -= 1;
                    Integer preLen = visited.getOrDefault(next[0], 0);
                    if (len > preLen) {
                        int curLen = Math.min(len, next[1]), nexLen = len - curLen;
                        // 本次新加的点
                        ans += curLen - preLen;
                        if (nexLen > 0) {
                            queue.add(next);
                        }
                        visited.put(next[0], nexLen);
                    }
                }
            }
        }
        return ans;
    }



    // 题解
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] adList = new List[n];
        for (int i = 0; i < n; i++) {
            adList[i] = new ArrayList<int[]>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            adList[u].add(new int[]{v, nodes});
            adList[v].add(new int[]{u, nodes});
        }
        Map<Integer, Integer> used = new HashMap<Integer, Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int reachableNodes = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty() && pq.peek()[0] <= maxMoves) {
            int[] pair = pq.poll();
            int step = pair[0], u = pair[1];
            if (!visited.add(u)) {
                continue;
            }
            reachableNodes++;
            for (int[] next : adList[u]) {
                int v = next[0], nodes = next[1];
                if (nodes + step + 1 <= maxMoves && !visited.contains(v)) {
                    pq.offer(new int[]{nodes + step + 1, v});
                }
                used.put(encode(u, v, n), Math.min(nodes, maxMoves - step));
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            reachableNodes += Math.min(nodes, used.getOrDefault(encode(u, v, n), 0) + used.getOrDefault(encode(v, u, n), 0));
        }
        return reachableNodes;
    }

    public int encode(int u, int v, int n) {
        return u * n + v;
    }
}
