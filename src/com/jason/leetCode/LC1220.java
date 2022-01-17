package com.jason.leetCode;

import java.util.Arrays;

public class LC1220 {
    public int countVowelPermutation(int n) {
        int mask = 1000000007;
        long ans = 0L;
        //最后一个字母分别为 a e i o u 的dp
        long[] pre = new long[5];
        long[] cur = new long[5];
        // a e i o u
        Arrays.fill(pre, 1L);
        for (int i = 2; i <= n; i++) {
            cur[0] = (pre[1] + pre[2] + pre[4]) % mask;
            cur[1] = (pre[0] + pre[2]) % mask;
            cur[2] = (pre[1] + pre[3]) % mask;
            cur[3] = pre[2] % mask;
            cur[4] = (pre[2] + pre[3]) % mask;
            System.arraycopy(cur, 0, pre, 0, 5);
        }
        for (int i = 0; i < 5; i++) {
            ans = (ans + pre[i]) % mask;
        }
        return (int) ans;
    }
}
