package com.jason.leetCode;

/**
 * @author Administrator
 */
public class LC944 {
    public int minDeletionSize(String[] strs) {
        int ans = 0;
        int row = strs.length;
        if (row <= 1) {
            return 0;
        }
        int col = strs[0].length();
        if (col == 0) {
            return 0;
        }
        for (int c = 0; c < col; c++) {
            for (int r = 1; r < row; r++) {
                if (strs[r].charAt(c) < strs[r - 1].charAt(c)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
