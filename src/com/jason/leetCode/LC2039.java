package com.jason.leetCode;

import java.util.*;

public class LC2039 {
    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        // 每个节点到0节点的距离，Dijkstra
        // 收到消息之后不再发送，计算收到回复之前最后一次发送时间，加上2倍的距离，就是这个节点空闲的时间
        int n = patience.length;
        // 每个点到0点的距离
        int[] distances = new int[n];
        List<Integer>[] connect = new List[n];
        for (int[] edge : edges) {
            connect[edge[0]] = connect[edge[0]] == null ? new ArrayList<>() : connect[edge[0]];
            connect[edge[0]].add(edge[1]);
            connect[edge[1]] = connect[edge[1]] == null ? new ArrayList<>() : connect[edge[1]];
            connect[edge[1]].add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                if (poll > 0 && distances[poll] == 0) {
                    distances[poll] = level;// 距离
                }
                List<Integer> next = connect[poll];
                for (Integer nx : next) {
                    if (nx > 0 && distances[nx] == 0) {
                        queue.add(nx);
                    }
                }
            }
            level++;//按层遍历
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int frequency = patience[i];
            int firstGet = distances[i] << 1;
//            int lastGet = ((firstGet - 1) / frequency) * frequency + firstGet;
            int lastGet = firstGet <= frequency ? firstGet : ((firstGet - 1) / frequency) * frequency + firstGet;
            ans = Math.max(ans, lastGet + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] edges = {{5, 7}, {15, 18}, {12, 6}, {5, 1}, {11, 17}, {3, 9}, {6, 11}, {14, 7}, {19, 13}, {13, 3}, {4, 12}, {9, 15}, {2, 10}, {18, 4}, {5, 14}, {17, 5}, {16, 2}, {7, 1}, {0, 16}, {10, 19}, {1, 8}};
        int[] patience = {0, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1};
//        int[][] edges = {{0, 1}, {1, 2}};
//        int[] patience = {0, 2, 1};
//        int[][] edges = {{0, 1}, {0, 2}, {1, 2}};
//        int[] patience = {0, 10, 10};
        System.out.println(networkBecomesIdle1(edges, patience));
        System.out.println(networkBecomesIdle(edges, patience));
    }


    static int N = 100010, M = N * 2, INF = 0x3f3f3f3f;
    static int[] he = new int[N], e = new int[M], ne = new int[M];
    static int[] dist = new int[N];
    static int idx;
    static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }
    public static int networkBecomesIdle1(int[][] edges, int[] patience) {
        Arrays.fill(he, -1);
        Arrays.fill(dist, INF);
        int n = patience.length;
        for (int[] e : edges) {
            add(e[0], e[1]);
            add(e[1], e[0]);
        }
        Deque<Integer> d = new ArrayDeque<>();
        d.addLast(0);
        dist[0] = 0;
        while (!d.isEmpty()) {
            int t = d.pollFirst();
            for (int i = he[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] != INF) continue;
                dist[j] = dist[t] + 1;
                d.addLast(j);
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int di = dist[i] * 2, t = patience[i];
            int cur = di <= t ? di : (di - 1) / t * t + di;
            if (cur > ans) ans = cur;
        }
        return ans + 1;
    }
}
