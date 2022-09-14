package com.jason.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author chenjieaj
 * @date 2022/9/14 8:57:40
 * @description
 */
public class LC1619 {
    public double trimMean(int[] arr) {
        int n = arr.length;
        int len = n / 20;
        Arrays.sort(arr);
        double sum = 0;
        for (int i = len; i < n - len; i++) {
            sum += arr[i];
        }
        return sum / (n - (len << 1));
    }

    public static double trimMean2(int[] arr) {
        int n = arr.length;
        int k = n / 20;
        // 存最小的k个数字
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (i, j) -> j - i);
        // 存最大的k个数字
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (i, j) -> i - j);
        for (int num : arr) {
            if (maxHeap.size() < k) {
                maxHeap.add(num);
            } else if (maxHeap.peek() > num) {
                maxHeap.poll();
                maxHeap.add(num);
            }
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (minHeap.peek() < num) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        while (!maxHeap.isEmpty()) {
            sum -= maxHeap.poll();
        }
        while (!minHeap.isEmpty()) {
            sum -= minHeap.poll();
        }
        return sum / (n - (k << 1));
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
        int[] arr = {6, 0, 7, 0, 7, 5, 7, 8, 3, 4, 0, 7, 8, 1, 6, 8, 1, 1, 2, 4, 8, 1, 9, 5, 4, 3, 8, 5, 10, 8, 6, 6, 1, 0, 6, 10, 8, 2, 3, 4};
        System.out.println(trimMean2(arr));
    }
}
