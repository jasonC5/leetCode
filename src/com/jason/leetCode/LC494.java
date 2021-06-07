package com.jason.leetCode;

import java.util.Arrays;

public class LC494 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        int targetSumWays = findTargetSumWays(nums, target);
        System.out.println(targetSumWays);
    }

    public static int findTargetSumWays(int[] nums, int target) {
        long arrSum = Arrays.stream(nums).boxed().mapToLong(x -> x).sum();//所有元素总和
        //target = addSum - subSum;
        //arrSum = addSum + subSum;
        //解一元二次方程
        //addSum = (target + arrSum) / 2  -- (target + arrSum) % 1 == 0 target + arrSum 是偶数，target + arrSum 要么都是奇数，要么都是偶数
        //subSum = (arrSum - target) / 2  -- (arrSum - target) % 1 == 0
        long doubleSub = arrSum-target;
        if (doubleSub < 0 || (doubleSub & 1) == 1) {//和是奇数，直接返回
            return 0;
        }
        //只要到这里必有答案
        int subSum = (int)(doubleSub / 2);//前面是减号的所有数的总和，剩下的动态规划就行了【背包问题】
        //一下不会，抄的答案，背包问题学完再看
        int length = nums.length;
        int[][] dp = new int[length + 1][subSum + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= length; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= subSum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[length][subSum];
    }
}
