package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/27 23:11:15
 * @description
 */
public class LC2027 {
    public int minimumMoves(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'X') {
                ans++;
                i += 2;
            }
        }
        return ans;
    }
}
