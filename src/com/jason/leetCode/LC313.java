package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author JasonC5
 */
public class LC313 {

//    public int nthSuperUglyNumber(int n, int[] primes) {
//        Set<Long> seen = new HashSet<Long>();
//        PriorityQueue<Long> heap = new PriorityQueue<Long>();
//        seen.add(1L);
//        heap.offer(1L);
//        int ugly = 0;
//        for (int i = 0; i < n; i++) {
//            long curr = heap.poll();
//            ugly = (int) curr;
//            for (int prime : primes) {
//                long next = curr * prime;
//                if (seen.add(next)) {
//                    heap.offer(next);
//                }
//            }
//        }
//        return ugly;
//    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.offer(1L);
        Set<Long> handled = new HashSet<>();
        handled.add(1L);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long num = heap.poll();
            ans = (int) num;
            for (int prime : primes) {
                long next = num * prime;
                if (!handled.contains(next)) {
                    heap.add(next);
                    handled.add(next);
                }
            }
        }
        return ans;
    }

    //动态规划
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(minNum, nums[j]);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (minNum == nums[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }
}

