package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 *
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 *
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 *
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 示例 2：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * 示例 3：
 *
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * 示例 4：
 *
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * 示例 5：
 *
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1482 {
    public static void main(String[] args) {
        int[] bloomDay = {1,10,3,10,2};
        int i = Solution.minDays(bloomDay, 3, 1);
        System.out.println(i);
    }

    static class Solution {
        public static int minDays(int[] bloomDay, int m, int k) {
            // 二分法
            int length = bloomDay.length;
            if (m * k > length) {
                return -1;
            }
            //只要m*k < 总花数，那么就一定存在结果
            int min = Arrays.stream(bloomDay).boxed().mapToInt(x -> x).min().getAsInt();
            int max = Arrays.stream(bloomDay).boxed().mapToInt(x -> x).max().getAsInt();
            //二分法获取
            while (min < max){
                int days = (min + max)/2;
                if (canFinish(bloomDay, m, k, days)){
                    //能做完，往前推
                    max = days;
                } else {
                    min = days + 1;
                }
            }
            return min;
        }

        private static boolean canFinish(int[] bloomDay, int m, int k, int days) {
            int bloomCount = 0;//记录当前开的花是连续的第几朵
            int packageCount = 0;//记录可以打包多少束花
            for (int i = 0; i < bloomDay.length; i++) {
                if (bloomDay[i] <= days){//开花了
                    if (++bloomCount >= k){//连续开花数够了，打包
//                        if (++packageCount >= m) {
//                            return true;
//                        }
                        bloomCount = 0;//归零
                    }//不够继续往下走
                } else {//没开花，连续终端了，
                    bloomCount = 0;//归零
                }
            }
            return packageCount>=m;
        }
    }
}
