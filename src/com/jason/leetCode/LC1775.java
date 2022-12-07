package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/7 10:06:08
 * @description
 */
public class LC1775 {
    public static int minOperations(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, dif = 0;
        if (6 * n < m || 6 * m < n) {
            return -1;
        }
        int[] cnt1 = new int[7];
        int[] cnt2 = new int[7];
        for (int num : nums1) {
            cnt1[num]++;
            dif += num;
        }
        for (int num : nums2) {
            cnt2[num]++;
            dif -= num;
        }
        if (dif == 0) {
            return 0;
        }
        return dif > 0 ? calc(cnt2, cnt1, dif) : calc(cnt1, cnt2, -dif);
    }

    private static int calc(int[] cnt1, int[] cnt2, int dif) {
        int[] h = new int[7];
        for (int i = 1; i < 7; ++i) {
            h[6 - i] += cnt1[i];
            h[i - 1] += cnt2[i];
        }
        int res = 0;
        for (int i = 5; i > 0 && dif > 0; --i) {
            int t = Math.min((dif + i - 1) / i, h[i]);
            res += t;
            dif -= t * i;
        }
        return res;
    }
}
