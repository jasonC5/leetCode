package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/29 9:22:51
 * @description
 */
public class LC1758 {
    public int minOperations(String s) {
        char[] chars = s.toCharArray();
        int ans1 = 0, ans2 = 0;
        char pre1 = '0', pre2 = '1', tmp;
        for (char c : chars) {
            if (c != pre1) {
                ans1++;
            }
            if (c != pre2) {
                ans2++;
            }
            tmp = pre1;
            pre1 = pre2;
            pre2 = tmp;
        }
        return Math.min(ans1, ans2);
    }
}
