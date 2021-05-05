package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除每个等于nums[i] - 1或nums[i] + 1的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC740 {

    public static void main(String[] args) {
        int[]nums = {3,4,2};
        System.out.println(Solution.deleteAndEarn(nums));
    }

    /**
     * 1.获取x的点数之后，需要删除所有x-1  x+1,相当于无法获取相邻的两个数的点数，可以参考198
     * 2.若获取了一个x的点数，删除完x-1 x+1后，也就能获取所有的x的点数
     * 3.算好每个位置的和，直接按照198的算法就能得出答案
     */
    public static class Solution {
        public static int deleteAndEarn(int[] nums) {
//            int[] sum = new int[nu];
            int max = Arrays.stream(nums).max().getAsInt();
            int sum[] = new int[max+1];
            for (int num : nums) {
                sum[num] += num;
            }
            return rob(sum);
        }

        public static int rob(int[] nums) {
            if (nums == null|| nums.length == 0) {
                return 0;
            } else if (nums.length == 1) {
                return nums[0];
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
            }
            return dp[dp.length-1];
        }
    }
}
