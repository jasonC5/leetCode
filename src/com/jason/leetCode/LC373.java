package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author JasonC5
 */
public class LC373 {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> nums1[i1[0]] + nums2[i1[1]] - nums1[i2[0]] - nums2[i2[1]]);
        for (int i = 0; i < nums1.length; i++) {
            minHeap.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] idxs = minHeap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxs[0]]);
            list.add(nums2[idxs[1]]);
            ans.add(list);
            if (idxs[1] + 1 < nums2.length) {
                minHeap.offer(new int[]{idxs[0], idxs[1] + 1});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2}, nums2 = {1, 2, 3};
        int k = 10;
        System.out.println(kSmallestPairs(nums1, nums2, k));
    }
}
