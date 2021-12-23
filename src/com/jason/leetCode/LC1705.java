package com.jason.leetCode;

import java.util.PriorityQueue;

public class LC1705 {
    public static int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        // 根据苹果的过期日期，小根堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        int eat = 0, i = 0;
        for (; i < n; i++) {
            minHeap.offer(new int[]{i + days[i], apples[i]});
            // 过期了的，或者吃完了的批次，丢掉
            while (!minHeap.isEmpty() && (minHeap.peek()[0] < i + 1 || minHeap.peek()[1] == 0)) {
                minHeap.poll();
            }
            // 此时堆里的，都是未过期的
            if (!minHeap.isEmpty()) {
                eat++;
                minHeap.peek()[1]--;
            }
        }
        // 不长苹果了，但是还能吃存货
        while (!minHeap.isEmpty()) {
            while (!minHeap.isEmpty() && (minHeap.peek()[0] < i + 1 || minHeap.peek()[1] == 0)) {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                eat++;
                minHeap.peek()[1]--;
            }
            i++;
        }
        return eat;
    }

    public static void main(String[] args) {
        int[] apple = {3, 0, 0, 0, 0, 2}, days = {3, 0, 0, 0, 0, 2};
        System.out.println(eatenApples(apple, days));
    }
}
