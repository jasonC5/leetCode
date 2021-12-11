package com.jason.leetCode;

public class LC372 {

    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int length = b.length;
        // 从低位到高位 a^ b[0]
        int ans = 1;
        int unit = a;
        for (int i = length - 1; i >= 0; i--) {
            // (a ^ n) ^ b[i]
            ans = (int) ((long) ans * pow(unit, b[i]) % MOD);
            // a^10 100 1000 ……
            unit = pow(unit, 10);
        }
        return ans;
    }

    // 快速幂
    private int pow(int unit, int n) {
        int ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) {// 多出一位，
                ans = (int) ((long) ans * unit % MOD);
            }
            // 平方
            unit = (int) ((long) unit * unit % MOD);
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 2;
        int[] b = {3};
        LC372 n = new LC372();
        System.out.println(n.superPow(a, b));
    }
}
