package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC2327 {
    static final long MOD = 1000000007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] knows = new long[n + 1];
        long[] forgets = new long[n + 1];
        long[] shares = new long[n + 1];
        knows[1] = 1;
        if (delay + 1 <= n) {
            shares[delay + 1] = 1;
        }
        if (forget + 1 <= n) {
            forgets[forget + 1] = 1;
        }
        //dp
        for (int i = 2; i <= n; i++) {
            knows[i] = (knows[i - 1] + shares[i] - forgets[i] + MOD) % MOD;
            if (forget + i <= n) {
                forgets[forget + i] = shares[i];
            }
            if (delay + i <= n) {
                shares[delay + i] = (shares[delay + i - 1] + shares[i] - forgets[delay + i] + MOD) % MOD;
            }
        }
        return (int) knows[n];
    }
}
