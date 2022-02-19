package com.jason.jingsai.double71;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC2163 {
    public long minimumDifference(int[] nums) {
        int length = nums.length;
        int n = length / 3;
        // 要求左右元素之和的差值的最小值，sumLeft - sumRight
        // 左边要尽可能小，右边要尽可能大==>左半部分尽可能减去大的，右半部分尽可能减去小的
        // 从左往右枚举分界线
        long ans = Long.MAX_VALUE;
        long[] minLeftSum = new long[n + 1], maxRightSum = new long[n + 1];
        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < n; i++) {
            minLeftSum[0] += nums[i];
            leftMaxHeap.add(nums[i]);
        }
        for (int i = n; i < n * 2; i++) {
            leftMaxHeap.add(nums[i]);
            minLeftSum[i + 1 - n] = minLeftSum[i - n] - leftMaxHeap.poll() + nums[i];
        }
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
        for (int i = n * 2; i < length; i++) {
            maxRightSum[n] += nums[i];
            rightMinHeap.add(nums[i]);
        }
        for (int i = n * 2 - 1; i >= n; i--) {
            rightMinHeap.add(nums[i]);
            maxRightSum[i - n] = maxRightSum[i - n + 1] - rightMinHeap.poll() + nums[i];
        }
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, minLeftSum[i] - maxRightSum[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {7, 9, 5, 8, 1, 3};
        System.out.println(new LC2163().minimumDifference(nums));
    }
}
