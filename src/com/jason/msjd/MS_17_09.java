package com.jason.msjd;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/9/28 8:55:13
 * @description
 */
public class MS_17_09 {
    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1L);
        Set<Long> visited = new HashSet<>();
        for (int i = 0; i < k - 1; i++) {
            Long poll = heap.poll();
            long x = poll * 3;
            long y = poll * 5;
            long z = poll * 7;
            if (!visited.contains(x)) {
                heap.add(x);
                visited.add(x);
            }
            if (!visited.contains(y)) {
                heap.add(y);
                visited.add(y);
            }
            if (!visited.contains(z)) {
                heap.add(z);
                visited.add(z);
            }
        }
        return heap.poll().intValue();
    }
}
