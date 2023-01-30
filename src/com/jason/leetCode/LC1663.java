package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/26 9:30:26
 * @description
 */
public class LC1663 {
    public static String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                x = k;
            } else if (k - 1 <= (n - 1 - i) * 26) {
                x = 1;
            } else if (k == (n - 1 - i) * 26) {
                x = 26;
            } else {
                x = k - 26 * (n - 1 - i);
            }
            sb.append((char) ('a' - 1 + x));
            k -= x;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        getSmallestString(3, 27);
    }
}
