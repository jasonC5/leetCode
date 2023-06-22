package com.jason.msjd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/6/22 23:21:19
 * @description
 */
public class MS_16_19 {

    public static final int[] LOCATION = {-1, 0, 1, -1, 1, 1, 0, -1, -1};
    public int m, n;

    public int[] pondSizes(int[][] land) {
        m = land.length;
        n = land[0].length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    ans.add(process(land, i, j));
                }
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    private int process(int[][] land, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || land[i][j] != 0) {
            return 0;
        }
        int count = 1;
        land[i][j] = -1;
        for (int k = 0; k < 8; k++) {
            count += process(land, i + LOCATION[k], j + LOCATION[k + 1]);
        }
        return count;
    }
}
