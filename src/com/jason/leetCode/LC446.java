package com.jason.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * <p>
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * <p>
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * <p>
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC446 {
    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 8, 10};
        System.out.println(numberOfArithmeticSlices(nums));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int ans = 0;
        int length = nums.length;
        Map<Long, Integer>[] dp = new Map[length];
        for (int i = 0; i < length; i++) {
            //已i结尾的子数组
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                //i和j的差值
                long sur = (long) nums[i] - nums[j];
                //在j的位置上的时候，有没有相同的等差
                Integer count = dp[j].getOrDefault(sur, 0);
                //有几个就能组成几个等差子数组
                ans += count;
                dp[i].put(sur, dp[i].getOrDefault(sur, 0) + count + 1);
            }
        }
        return ans;
    }


}
