package com.jason.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC786 {
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int length = arr.length;
        // 门槛堆
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((c1, c2) -> c2[0] * c1[1] - c1[0] * c2[1]);
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                int[] num = new int[]{arr[j], arr[i]};
                maxHeap.offer(num);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {1,7};
        int k = 1;
        int[] ans = kthSmallestPrimeFraction(arr, k);
        System.out.println(Arrays.toString(ans));
    }

}
