package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/2/27 9:36:23
 * @description
 */
public class LC1144 {
    public static int movesToMakeZigzag(int[] nums) {
        return Math.min(process(nums, 0), process(nums, 1));
    }

    private static int process(int[] nums, int start) {
        int ans = 0;
        for (int i = start; i < nums.length; i += 2) {
            int tmp = 0;
            if (i - 1 >= 0 && nums[i] >= nums[i - 1]) {
                tmp = nums[i] - nums[i - 1] + 1;
            }
            if (i + 1 < nums.length && nums[i] >= nums[i + 1]) {
                tmp = Math.max(tmp, nums[i] - nums[i + 1] + 1);
            }
            ans += tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        movesToMakeZigzag(new int[]{1, 2, 3});
    }
}
