package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且3 * k 项的和最大的子数组，并返回这三个子数组。
 * <p>
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC689 {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int length = nums.length;
        long[] preSum = new long[length - k + 1];// 每个位置开始，连续k个数 的和
        for (int i = 0; i < k; i++) {
            preSum[0] += nums[i];
        }
        for (int i = 1; i <= length - k; i++) {
            preSum[i] += preSum[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        // 从preSum中找到 3个数，每个数间隔 >= k，找到和最大的情况   如果暴力推： O(n^3)  nums.length <= 2 * 10^4  肯定会超时
        int p1 = 0, p2 = k, p3 = 2 * k;
        int temp = 0;
        long oneMax = preSum[p1];
        long twoMax = preSum[p1] + preSum[p2];
        long threeMax = twoMax + preSum[p3];
        int[] ans = new int[]{p1, p2, p3};
        for (int i = p3 + 1; i <= length - k; i++) {
            if (preSum[i - 2 * k] > oneMax) {
                oneMax = preSum[i - 2 * k];
                temp = i - 2 * k;
            }
            if (preSum[i - k] + oneMax > twoMax) {
                twoMax = preSum[i - k] + oneMax;
                p1 = temp;
                p2 = i - k;
            }
            if (preSum[i] + twoMax > threeMax) {
                threeMax = preSum[i] + twoMax;
                ans[0] = p1;
                ans[1] = p2;
                ans[2] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,1,2,1,2,1};
        int k = 2;
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, k)));
    }
}
