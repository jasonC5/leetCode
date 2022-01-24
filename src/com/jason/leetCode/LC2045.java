package com.jason.leetCode;

import java.util.*;

/**
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 * <p>
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 * <p>
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 * <p>
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * <p>
 * 注意：
 * <p>
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * 输出：13
 * 解释：
 * 上面的左图展现了给出的城市交通图。
 * 右图中的蓝色路径是最短时间路径。
 * 花费的时间是：
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 4：3 分钟，总花费时间=3
 * - 4 -> 5：3 分钟，总花费时间=6
 * 因此需要的最小时间是 6 分钟。
 * <p>
 * 右图中的红色路径是第二短时间路径。
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 3：3 分钟，总花费时间=3
 * - 3 -> 4：3 分钟，总花费时间=6
 * - 在节点 4 等待 4 分钟，总花费时间=10
 * - 4 -> 5：3 分钟，总花费时间=13
 * 因此第二短时间是 13 分钟。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, edges = [[1,2]], time = 3, change = 2
 * 输出：11
 * 解释：
 * 最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
 * 最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC2045 {
    //内存超了
    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        // 每个点的联通点
        Map<Integer, List<Integer>> accessibleListMap = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> points = accessibleListMap.getOrDefault(edge[0], new ArrayList<>());
            points.add(edge[1]);
            accessibleListMap.put(edge[0], points);
            points = accessibleListMap.getOrDefault(edge[1], new ArrayList<>());
            points.add(edge[0]);
            accessibleListMap.put(edge[1], points);
        }
        // 小根堆 [点，时间]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
        // 第几次到达，到达时间
        int count = 0, arrivalTime = 0;
        minHeap.offer(new int[]{1, 0});
        while (!minHeap.isEmpty()) {
            int[] info = minHeap.poll();
            List<Integer> nexts = accessibleListMap.get(info[0]);
            int curTime = info[1];
            // 看是红灯，还是绿灯，如果是红灯，需要等待红灯结束，如果是绿灯，直接通行
            if (((curTime / change) & 1) == 1) {
                //等红灯
                curTime = (curTime / change + 1) * change;
            }
            //开过去
            curTime += time;
            for (Integer next : nexts) {
                if (next == n) {
                    if (count == 0) {//第一次到达
                        count++;
                        arrivalTime = curTime;
                        minHeap.offer(new int[]{next, curTime});
                    } else if (arrivalTime != curTime) {//严格第二次到达
                        return curTime;
                    }//不是第一次到达，但是时间相同，不处理(和第一次一模一样的数据，不影响答案)
                } else {
                    minHeap.offer(new int[]{next, curTime});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 5, time = 3, change = 5;
        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}};
//        int n = 2, time = 3, change = 2;
//        int[][] edges = {{1, 2}};
        System.out.println(secondMinimum2(n, edges, time, change));
    }

    public static int secondMinimum2(int n, int[][] edges, int time, int change) {
        // 每个点的联通点
        Map<Integer, List<Integer>> accessibleListMap = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> points = accessibleListMap.getOrDefault(edge[0], new ArrayList<>());
            points.add(edge[1]);
            accessibleListMap.put(edge[0], points);
            points = accessibleListMap.getOrDefault(edge[1], new ArrayList<>());
            points.add(edge[0]);
            accessibleListMap.put(edge[1], points);
        }
        // 小根堆 [点，时间]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
        // 每个点最多到两次就够了
        int[] counts = new int[n + 1];
        // 每个点到达的时间（可更新）
        int[] times = new int[n + 1];
        minHeap.offer(new int[]{1, 0});
        while (!minHeap.isEmpty()) {
            int[] info = minHeap.poll();
            List<Integer> nexts = accessibleListMap.get(info[0]);
            int curTime = info[1];
            // 看是红灯，还是绿灯，如果是红灯，需要等待红灯结束，如果是绿灯，直接通行
            if (((curTime / change) & 1) == 1) {
                //等红灯
                curTime = (curTime / change + 1) * change;
            }
            //开过去
            curTime += time;
            for (Integer next : nexts) {
                if (counts[next] == 0) {// 第一次到
                    counts[next]++;
                    times[next] = curTime;
                    minHeap.offer(new int[]{next, curTime});
                } else if (counts[next] == 1 && times[next] != curTime) {//不是第一次到，而且时间不一致
                    if (next == n) {
                        return curTime;
                    } else {
                        counts[next]++;
                        times[next] = curTime;
                        minHeap.offer(new int[]{next, curTime});
                    }
//                } else {
//                    // 再往后的次数，或者时间相等，就不往堆里放了，
                }
            }
        }
        return -1;
    }
}
