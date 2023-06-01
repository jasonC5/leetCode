package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/6/1 10:53:33
 * @description
 */
public class LC2517 {


    public int maximumTastiness(int[] price, int k) {
        int n = price.length;
        Arrays.sort(price);
        int left = 0, right = price[n - 1], ans = 0, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (check(price, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] price, int k, int n) {
        int pre = Integer.MIN_VALUE / 2, cnt = 0;
        for (int i : price) {
            if (i - pre >= n) {
                cnt++;
                pre = i;
            }
        }
        return cnt >= k;
    }


}
