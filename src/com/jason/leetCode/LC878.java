package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/22 11:09:53
 * @description
 */
public class LC878 {
    static final int MOD = 1000000007;
    // 二分答案法
    public int nthMagicalNumber(int n, int a, int b) {
        long left = Math.min(a, b);
        long right = left * n;
        int c = lcm(a, b);//最小公倍数（同时有a和b的因子）
        while (left <= right) {
            long mid = (left + right) >> 1;
            long cnt = mid / a + mid / b - mid / c;
            if (cnt >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return (int) ((right + 1) % MOD);
    }

    // 最小公倍数
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 最大公约数
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
