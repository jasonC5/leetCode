package com.jason.leetCode;

import java.util.*;

/**
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC368 {

    public static void main(String[] args) {
        int[] nums = {4,8,10,240};
        List<Integer> list = Solution.largestDivisibleSubset(nums);
        System.out.println(list.toArray().toString());
    }


    public static class Solution {
        public static List<Integer> largestDivisibleSubset(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);//从小到大排序
            Set<Integer>[] dp = new Set[len];
            dp[0] = new HashSet<>();
            dp[0].add(nums[0]);
            List<Integer> result = new ArrayList<>(dp[0]);
            for (int i = 1; i < len; i++) {
                final int numi = nums[i];
                dp[i] = new HashSet<>();
                for (int j = 0; j < i; j++) {
                    if (numi % nums[j] == 0 && dp[j].size() > dp[i].size()) {
                        dp[i].clear();
                        dp[i].addAll(dp[j]);//数组里面互不相同，而且是set所以pupa重复添加
                    }
                }
                dp[i].add(numi);
                if (result.size() < dp[i].size()){
                    result = new ArrayList<>(dp[i]);
                }
            }
            return result;
        }
    }

}
