package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/4 9:04:14
 * @description
 */
public class LC1802 {
    public static int maxValue(int n, int index, int maxSum) {
        // 二分答案法
        int left = 0, right = maxSum, ans = 1, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (match(n, index, maxSum, mid)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private static boolean match(int n, int index, int maxSum, int num) {
        // 三部分 左边 + num + 右边
        long left = sum(num - 1, index);
        long right = sum(num - 1, n - 1 - index);
        return num + left + right <= maxSum;
    }

    private static long sum(int max, int len) {
        if (len <= 0) {
            return 0L;
        }
        if (max >= len) {
            int s = max + 1 - len;
            return (long) (max + s) * len / 2;
        } else {
            int o = len - max;
            return (long) (max + 1) * max / 2 + o;
        }
    }

    public static void main(String[] args) {
        System.out.println(maxValue(1000000000, 100000000, 1000000000));
    }
}
