package com.jason.leetCode;

public class LC829 {
    public int consecutiveNumbersSum(int n) {
        int ans = 1;
        for (int i = 2; i * (i + 1) <= 2 * n; i++) {
            ans += ((i & 1) == 1 && n % i == 0) || ((i & 1) == 0 && n % i != 0 && (n << 1) % i == 0) ? 1 : 0;
        }
        return ans;
    }
}
