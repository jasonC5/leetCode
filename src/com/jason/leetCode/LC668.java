package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/5/18 8:45:54
 * @description
 */
public class LC668 {
    public static int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n, ans = 1;
        while (right >= left) {
            int mid = left + ((right - left) >> 1);
            int leNum = lessThen(Math.min(m, n), Math.max(m, n), mid);
            if (leNum >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int lessThen(int m, int n, int num) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int left = 1, right = n, curCount = 0;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int cur = mid * i;
                if (cur <= num) {
                    curCount = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            count += curCount;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(findKthNumber(3, 3, 5));
        System.out.println(findKthNumber(2, 3, 6));
        System.out.println(findKthNumber(42, 34, 401));//126
    }
}
