package com.jason.leetCode;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chenjieaj
 * @date 2022/5/20 8:49:39
 * @description
 */
public class LC436 {
    public static int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] ans = new int[length];
        TreeMap<Integer, Integer> container = new TreeMap<>();
        for (int i = 0; i < length; i++) {
            container.put(intervals[i][0], i);
        }
        for (int i = 0; i < length; i++) {
            Map.Entry<Integer, Integer> entry = container.ceilingEntry(intervals[i][1]);
            ans[i] = entry == null ? -1 : entry.getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2}};
        System.out.println(Arrays.toString(findRightInterval(intervals)));
    }
}
