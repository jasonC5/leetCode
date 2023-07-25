package com.jason.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author chenjieaj
 * @date 2023/7/25 9:48:25
 * @description
 */
public class LC2208 {
    public int halveArray(int[] nums) {
        double sum = 0;
        // 大根堆
        PriorityQueue<Double> heap = new PriorityQueue<>((d1, d2) -> d2.compareTo(d1));
        for (int num : nums) {
            heap.offer((double) num);
            sum += num;
        }
        double half = (double) sum / 2;
        double reduce = 0D;
        int times = 0;
        while (reduce < half) {
            Double poll = heap.poll();
            reduce += poll / 2;
            times++;
            heap.add(poll / 2);
        }
        return times;
    }
}
