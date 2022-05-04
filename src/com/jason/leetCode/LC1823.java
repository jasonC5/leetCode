package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1823 {
    public int findTheWinner(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                q.add(q.poll());
            }
            q.poll();
        }
        return q.poll();
    }
}
