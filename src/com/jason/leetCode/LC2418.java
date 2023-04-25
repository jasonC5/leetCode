package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/4/25 14:44:20
 * @description
 */
public class LC2418 {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Object[][] objs = new Object[n][2];
        for (int i = 0; i < n; i++) {
            objs[i][0] = names[i];
            objs[i][1] = heights[i];
        }
        Arrays.sort(objs, (o1, o2) -> (int) o2[1] - (int) o1[1]);
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (String) objs[i][0];
        }
        return ans;
    }
}
