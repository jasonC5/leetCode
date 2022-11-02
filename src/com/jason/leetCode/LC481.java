package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/31 9:16:43
 * @description
 */
public class LC481 {
    public int magicalString(int n) {
        if (n < 4) {
            return 1;
        }
        int[] num = new int[n];
        num[0] = 1;
        num[1] = 2;
        num[2] = 2;
        int cnt = 1, cur = 2, end = 3;
        while (end < n) {
            int size = num[cur];
            int curNum = 3 - num[end - 1];
            while (end < n && size > 0) {
                num[end++] = curNum;
                if (curNum == 1) {
                    cnt++;
                }
                size--;
            }
            cur++;
        }
        return cnt;
    }
}
