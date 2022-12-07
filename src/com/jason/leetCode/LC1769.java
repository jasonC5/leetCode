package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/12/2 9:35:02
 * @description
 */
public class LC1769 {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                points.add(i);
            }
        }

        for (int cur, i = 0; i < n; i++) {
            cur = 0;
            for (Integer point : points) {
                cur += Math.abs(point - i);
            }
            ans[i] = cur;
        }
        return ans;
    }
}
