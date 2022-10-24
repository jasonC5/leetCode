package com.jason.leetCode;

/**
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * <p>
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 * <p>
 * 用例可以保证存在这样的划分方法。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 *
 * @author chenjieaj
 * @date 2022/10/24 9:18:20
 * @description
 */
public class LC915 {
    public static int partitionDisjoint(int[] nums) {
        int n = nums.length;
        // 预处理数组， 左边最大的，要小于等于右边最小的
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        leftMax[0] = nums[0];
        rightMin[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
            rightMin[n - 1 - i] = Math.min(rightMin[n - i], nums[n - 1 - i]);
        }
        for (int i = 1; i < n; i++) {
            if (leftMax[i - 1] <= rightMin[i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {5, 0, 3, 8, 6};
        System.out.println(partitionDisjoint(nums));
    }
}
