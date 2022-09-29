package com.jason.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author chenjieaj
 * @date 2022/9/29 16:25:47
 * @description
 */
public class LC857 {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] info = new double[n][2];
        for (int i = 0; i < n; i++) {
            info[i][0] = wage[i] * 1.0 / quality[i];// 最低工资 / 质量
            info[i][1] = quality[i];// 最低工资
        }
        // 按照性价比从低到高排序，到k了之后直接用最大的来算
        Arrays.sort(info, (i, j) -> Double.compare(i[0], j[0]));
        // 金额，大跟堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        double ans = Double.MAX_VALUE;
        for (int i = 0, total = 0; i < n; i++) {
            int cur = (int) info[i][1];//当前的最小价格
            total += cur;
            maxHeap.add(cur);
            if (maxHeap.size() > k) {
                Integer poll = maxHeap.poll();
                total -= poll;
                if (poll == cur) {// 自己被弹出来了，不计算了
                    continue;
                }
            }
            if (maxHeap.size() == k) {
                ans = Math.min(ans, total * info[i][0]);
            }
        }
        return ans;
    }
}
