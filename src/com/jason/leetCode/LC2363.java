package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/2/28 9:03:41
 * @description
 */
public class LC2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int[] container = new int[1001];
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] ints : items1) {
            container[ints[0]] += ints[1];
        }
        for (int[] ints : items2) {
            container[ints[0]] += ints[1];
        }
        for (int i = 0; i < container.length; i++) {
            if (container[i] > 0) {
                List<Integer> a = new ArrayList<>();
                a.add(i);
                a.add(container[i]);
                ans.add(a);
            }
        }
        return ans;
    }
}
