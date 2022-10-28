package com.jason.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k
 *
 * @author chenjieaj
 * @date 2022/10/26 9:22:15
 * @description
 */
public class LC862 {
    public int shortestSubarray(int[] nums, int k) {
        // 前缀和 + 单调队列
        int n = nums.length, ans = Integer.MAX_VALUE;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long cur = preSum[i];
            // 如果能第一次匹配到，就无需再考虑后面的位置，反正也不会比当前短
            while (!q.isEmpty() && cur - preSum[q.peekFirst()] >= k) {
                ans = Math.min(ans, i - q.pollFirst());
            }
            // 我前面比我还大的，肯定没我有优势：和它能组成>=k，和我肯定更可以，而且还更短
            while (!q.isEmpty() && cur <= preSum[q.peekLast()]) {
                q.pollLast();
            }
            q.addLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
