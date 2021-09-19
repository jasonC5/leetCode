package com.jason.leetCode;

public class LC292 {

    public static boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        //进入对手环节
        for (int i = 1; i <= 3; i++) {
            if (!canWinNim(n - i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canWinNim2(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;
        for (int k = 4; k <= n; k++) {
            for (int i = 1; i <= 3; i++) {
                if (!dp[k - i]) {
                    dp[k] = true;
                }
            }
        }
        return dp[n];
    }

    public static boolean canWinNim3(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] dp = {true, true, true};
        boolean ans = false;
        for (int k = 4; k <= n; k++) {
            ans = !(dp[0] & dp[1] & dp[2]);
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = ans;
        }
        return ans;
    }

    public static boolean canWinNim4(int n) {
        return n % 4 != 0;
    }

    public static boolean canWinNim5(int n) {
        return (n & 3) != 0;
    }

    public static void main(String[] args) {
//        System.out.println(canWinNim(7));
//        System.out.println(canWinNim2(7));
        for (int i = 1; i < 50000; i++) {
            System.out.println(canWinNim3(i));
            if (canWinNim4(i) ^ canWinNim5(i)) {
                System.out.println("error: " + i);
                return;
            }
        }
        System.out.println("====================finished================");
    }
}
