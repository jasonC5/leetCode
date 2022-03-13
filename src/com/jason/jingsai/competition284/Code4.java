package com.jason.jingsai.competition284;

import java.util.*;

public class Code4 {

//    static class Info {
//        int point;
//        long wight;
//        Set<Integer> ways;
//
//        public Info(int point, long wight, Set<Integer> ways) {
//            this.point = point;
//            this.wight = wight;
//            this.ways = ways;
//        }
//    }
//
//    public static long minimumWeightMy(int n, int[][] edges, int src1, int src2, int dest) {
//        boolean[] find = new boolean[n];
//        Map<Integer, Info> infoMap = new HashMap<>();
//        Map<Integer, List<int[]>> map = new HashMap<>();
//        for (int[] edge : edges) {
//            List<int[]> list = map.getOrDefault(edge[0], new ArrayList<>());
//            list.add(new int[]{edge[1], edge[2]});// 能到哪，权重多少
//        }
//        Set<Integer> targets = new HashSet<Integer>() {{
//            this.add(dest);
//        }};
//        Info info1 = find(src1, targets, map, n);
//        long p1 = Long.MAX_VALUE;
//        if (info1 != null) {
//            p1 = info1.wight;
//            Info info2 = find(src2, info1.ways, map, n);
//            if (info2 != null) {
//                p1 += info2.wight;
//            } else {
//                p1 = Long.MAX_VALUE;
//            }
//        }
//        long p2 = Long.MAX_VALUE;
//        info1 = find(src2, targets, map, n);
//        if (info1 != null) {
//            p2 = info1.wight;
//            Info info2 = find(src1, info1.ways, map, n);
//            if (info2 != null) {
//                p2 += info2.wight;
//            } else {
//                p2 = Long.MAX_VALUE;
//            }
//        }
//        long ans = Math.min(p1, p2);
//        return ans == Long.MAX_VALUE ? -1 : ans;
//    }
//
//    // 从source到targets的最小权重和路径
//    public static Info find(int source, Set<Integer> targets, Map<Integer, List<int[]>> map, int n) {
//        Info[] weights = new Info[n];
//        PriorityQueue<Info> heap = new PriorityQueue<Info>((i1, i2) -> Long.compare(i1.wight, i2.wight));
//        Info sourceInfo = new Info(source,0, new HashSet<Integer>(){{this.add(source);}});
//        heap.add(sourceInfo);
//        while (!heap.isEmpty()){
//            Info poll = heap.poll();
//            int point = poll.point;
//            if (targets.contains(point)){
//                return poll;
//            } else if (weights[point] == null){
//                weights[point] = poll;
//                List<int[]> nexts = map.get(weights[point]);
//                for (int[] next : nexts) {
//
//                }
//            }
//        }
//    }

    public static long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<long[]>[] toList = new ArrayList[n], fromList = new ArrayList[n];
        for (int[] edge : edges) {
            int form = edge[0];
            int to = edge[1];
            int wight = edge[2];
            toList[form] = toList[form] == null ? new ArrayList<>() : toList[form];
            toList[form].add(new long[]{to, wight});
            fromList[to] = fromList[to] == null ? new ArrayList<>() : fromList[to];
            fromList[to].add(new long[]{form, wight});
        }
        Long[] const1 = dijkstra(src1, toList);
        Long[] const2 = dijkstra(src2, toList);
        Long[] constDest = dijkstra(dest, fromList);
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (const1[i] != null && const2[i] != null && constDest[i] != null) {
                min = Math.min(min, const1[i] + const2[i] + constDest[i]);
            }
        }
        return min == Long.MAX_VALUE ? -1 : min;
    }

    // 单元最短路径模板
    private static Long[] dijkstra(int src, List<long[]>[] toList) {
        int n = toList.length;
        Long[] ans = new Long[n];
        PriorityQueue<long[]> heap = new PriorityQueue<long[]>((l1, l2) -> Long.compare(l1[1], l2[1]));
        heap.add(new long[]{src, 0});
        while (!heap.isEmpty()) {
            long[] poll = heap.poll();
            int idx = (int) poll[0];
            long cost = poll[1];
            if (ans[idx] == null) {
                ans[idx] = poll[1];
                if (toList[idx] != null) {
                    for (long[] next : toList[idx]) {
                        heap.add(new long[]{next[0], next[1] + cost});
                    }
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 6;//6个点 0~5
        int[][] edge = {{0, 2, 2}, {0, 5, 6}, {1, 0, 3}, {1, 4, 5}, {2, 1, 1}, {2, 3, 3}, {2, 3, 4}, {3, 4, 2}, {4, 5, 1}};
        int src1 = 0, src2 = 1, dest = 5;
        System.out.println(minimumWeight_other(n, edge, src1, src2, dest));
        System.out.println(minimumWeight(n, edge, src1, src2, dest));
    }

    public static long minimumWeight_other(int n, int[][] edges, int src1, int src2, int dest) {
        ArrayList<long[]>[] list = new ArrayList[n], list2 = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            list2[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            list[edge[0]].add(new long[]{edge[1], edge[2]});//  edge[0]-->edg[1]  权重  我能到谁
            list2[edge[1]].add(new long[]{edge[0], edge[2]});// edge[1]-->edge[0] 权重  谁能到我
        }
        Long s1[] = minimumWeight_other(src1, list), s2[] = minimumWeight_other(src2, list), d[] = minimumWeight_other(dest, list2),
                min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = s1[i] == null || s2[i] == null || d[i] == null ? min : Math.min(min, s1[i] + s2[i] + d[i]);
        }
        return min < Long.MAX_VALUE ? min : -1;
    }

    //单元最小路径
    private static Long[] minimumWeight_other(int src, ArrayList<long[]>[] list) {
        Long[] dist = new Long[list.length], poll;
        PriorityQueue<Long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (queue.offer(new Long[]{0L, (long) src}); (poll = queue.poll()) != null; ) {
            if (dist[poll[1].intValue()] == null) {
                dist[poll[1].intValue()] = poll[0];
                for (long[] edge : list[poll[1].intValue()]) {
                    queue.offer(new Long[]{poll[0] + edge[1], edge[0]});
                }
            }
        }
        return dist;
    }
}
