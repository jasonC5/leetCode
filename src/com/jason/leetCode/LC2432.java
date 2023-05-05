package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/5/5 9:59:32
 * @description
 */
public class LC2432 {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0;
        for (int i = 0, pre = 0, max = 0, time; i < logs.length; i++) {
            time = logs[i][1] - pre;
            pre = logs[i][1];
            if (time > max) {
                max = time;
                ans = logs[i][0];
            } else if (time == max) {
                ans = Math.min(ans, logs[i][0]);
            }
        }
        return ans;
    }
}
