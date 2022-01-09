package com.jason.jingsai.competition275;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/contest/weekly-contest-275/problems/check-if-every-row-and-column-contains-all-numbers/
 * @author JasonC5
 */
public class C5976 {
    public static boolean checkValid(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            Set<Integer> rowExisted = new HashSet<>();
            Set<Integer> colExisted = new HashSet<>();
            for (int j = 0; j < len; j++) {
                if (rowExisted.contains(matrix[i][j])) {
                    return false;
                } else {
                    rowExisted.add(matrix[i][j]);
                }
                if (colExisted.contains(matrix[j][i])) {
                    return false;
                } else {
                    colExisted.add(matrix[j][i]);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 2, 3}, {1, 2, 3}};
        System.out.println(checkValid(matrix));
    }
}
