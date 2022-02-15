package com.jason.leetCode;

import java.lang.reflect.Array;
import java.util.*;

public class LC1380 {

    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowMin = new int[m];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        int[] colMax = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }
        Set<Integer> rowMinSet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            rowMinSet.add(rowMin[i]);
        }
        List<Integer> ans = new ArrayList<>();
        for (int cm : colMax) {
            if (rowMinSet.contains(cm)) {
                ans.add(cm);
            }
        }
        return ans;
    }
}
