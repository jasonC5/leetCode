package com.jason.leetCode;

import java.util.Arrays;

public class LC473 {
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if ((sum & 3) != 0) {
            return false;
        }
        int length = sum >> 2;
        int[] arr = new int[4];
        Arrays.fill(arr, length);
        // 从大到小排序，有助于尽快失败，剪枝
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        return process(matchsticks, arr, 0);
    }

    private boolean process(int[] matchsticks, int[] arr, int idx) {
        if (idx == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= matchsticks[idx];
            if (arr[i] >= 0 && process(matchsticks, arr, idx + 1)) {
                return true;
            }
            // 回溯
            arr[i] += matchsticks[idx];
        }
        return false;
    }

}
