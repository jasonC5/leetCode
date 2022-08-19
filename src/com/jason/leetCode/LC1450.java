package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/19 9:14:51
 * @description
 */
public class LC1450 {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        for (int i = 0; i < startTime.length; i++) {
            ans += (startTime[i] <= queryTime && endTime[i] >= queryTime) ? 1 : 0;
        }
        return ans;
    }
}
