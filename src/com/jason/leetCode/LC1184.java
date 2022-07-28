package com.jason.leetCode;

/**
 * @author chenjie
 * @date 2022/7/24 14:50:26
 * @description
 */
public class LC1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int all = 0, d1 = 0, min = Math.min(start, destination), max = Math.max(start, destination);
        for (int i = 0; i < distance.length; i++) {
            all += distance[i];
            d1 += i >= min && i < max ? distance[i] : 0;
        }
        return Math.min(d1, all - d1);
    }
}
