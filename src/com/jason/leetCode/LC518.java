package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC518 {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        int change = change(amount, coins);
        System.out.println(change);
    }

    public static int change(int amount, int[] coins) {
        //动态规划
        //1.dp[]记录从0开始，使用给定硬币有多少种方法可以组成该金额
        //2.金额为0时，有一种情况，什么都不取 dp[0] = 1;
        //3.金额为i时，往前找，若能找到针对一个硬币金额coins[j],dp[i-coins[j]]>0则加上上面对应的金额
        //4.拿到dp[amount]就是结果
        int[] dp = new int[amount + 1];
        dp[0] = 1;
//
//        for (int j = 0; j < coins.length; j++) {//硬币面额
//            for (int i = coins[j]; i <= amount; i++) {//能凑成的总金额，
//                if (i - coins[j] >= 0) {
//                    dp[i] += dp[i - coins[j]];
//                }
//            }
//        }
        //这种解法会有重复值如：4= 2+2 = 1+1+2，4 = 3+1 = 1+2+1，这两种就重了
        for (int i = 1; i <= amount; i++) {//能凑成的总金额，
            for (int j = 0; j < coins.length; j++) {//硬币面额
                if (i >= coins[j]) {
                    dp[i] += dp[i - coins[j]];
                }
            }
            //去重？

        }

//        for (int coin : coins) {
//            for (int i = coin; i <= amount; i++) {
//                dp[i] += dp[i - coin];
//            }
//        }

        return dp[amount];
    }
}
