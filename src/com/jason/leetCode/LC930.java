package com.jason.leetCode;

import java.util.HashMap;

/**
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * <p>
 * 子数组 是数组的一段连续部分。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC930 {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
//        int[] nums = {0, 0, 0, 0, 0};
//        int goal = 0;
        int i = numSubarraysWithSum(nums, goal);
        System.out.println(i);
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        if (nums == null || nums.length == 0 || goal < 0) {
            return 0;
        }
        int length = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int checkSum = 0;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            map.put(checkSum, map.getOrDefault(checkSum, 0) + 1);
            checkSum += nums[i];
            ans += map.getOrDefault(checkSum - goal, 0);
        }
        return ans;
    }
}
