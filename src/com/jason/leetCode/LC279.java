package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class LC279 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(numSquares(12));
        long start = System.currentTimeMillis();
        int times = 1000;
        int maxVal = 99999;
        for (int i = 0; i < times; i++) {
            int num = (int) (Math.random() * maxVal) + 1;
//            int ans1 = numSquares(num);
//            int ans2 = numSquares2(num);
//            7144
//            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->LC279.numSquares(num));
//            CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->LC279.numSquares2(num));
//            //7210
//            Integer ans1 = future.get();
//            Integer ans2 = future2.get();
//            if (!ans1.equals(ans2)) {
////            if (ans1 != ans2) {
//                System.out.println("failed num = " + num + ",ans1 = " + ans1 + ",ans2=" + ans2);
//                return;
//            }
            //7035
//            Integer integer = CompletableFuture.supplyAsync(() -> LC279.numSquares(num))
//                    .thenApplyAsync((count) -> count - LC279.numSquares2(num))
//                    .get();
//            if (integer != 0){
//                System.out.println("failed num = " + num);
//                return;
//            }
            //6949
            final int[] ansArr = new int[2];
            CompletableFuture
                    .runAsync(() -> ansArr[0] = LC279.numSquares(num))
                    .thenRunAsync(() -> ansArr[1] = LC279.numSquares2(num)).join();
            if (ansArr[0] != ansArr[1]) {
                System.out.println("failed num = " + num);
                return;
            }
        }
        System.out.println("complete!!!");
        long end = System.currentTimeMillis();
        System.out.println("TimeMillis used:" + (end - start));
    }


    public static int numSquares(int n) {
//        //硬算--核心问题：找到小于该数字的最大的完全平方数 假设为x
//        int ans = 0;
//        while(n > 0){
//            int x = findMaxSquareNum(n);//思路错误： 比如离12最近的完全平方数是9,9要凑12=9+1+1+1（4），但是最优解却是4+4+4（3）
//            ans += n/x;
//            n %= x;
//        }
//        return ans;
        //动态规划
        //能组成n的数：1^2   2^2   3^2   4^2 …… j^2   n 减去其中一个之后  n-j^2 继续要找能组成 n-j^2 的完全平方数，直到  n-j^2=0【从后往前的过程】
        //动态规划思想：从0开始，
        // dp[0] = 0;
        // dp[1] = 1;
        // dp[2] = dp[2-1^2]+1 = 1+1 = 2;
        // dp[3] = dp[3-1^2] = 3 ;
        // db[4] = min(dp[4-1^2]+1 , dp[4-2^2]+1) = min(4,1) = 1;
        // dp[5] = min(dp[5-1^2]+1 , dp[5-2^2]+1) = min(dp[4]+1, dp[1]+1) = 2
        // ……
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
//    //查找离n最近的完全平方数
//    public static int findMaxSquareNum(int n) {
//        return (int)Math.sqrt(n);
//    }


    public static int numSquares2(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public static boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public static boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
}
