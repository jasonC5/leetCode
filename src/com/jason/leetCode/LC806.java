package com.jason.leetCode;

public class LC806 {
    public int[] numberOfLines(int[] widths, String s) {
        int row = 1, len = 0, curLen;
        for (char c : s.toCharArray()) {
            curLen = widths[c - 'a'];
            if (len + curLen > 100) {
                row++;
                len = curLen;
            } else {
                len += curLen;
            }
        }
        return new int[]{row, len};
    }
}
