package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/20 10:27:39
 * @description
 */
public class LC1760 {
    // 二分答案法
    public static int minimumSize(int[] nums, int maxOperations) {
        int l = 0, r = 0, mid;
        for (int num : nums) {
            r = Math.max(r, num);
        }
        int ans = r;
        if (ans <= 1) {
            return ans;
        }
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (can(nums, maxOperations, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static boolean can(int[] nums, int maxOperations, int maxValue) {
        for (int num : nums) {
            if (num > maxValue) {
                maxOperations -= (num - 1) / maxValue;
            }
        }
        return maxOperations >= 0;
    }

    public static void main(String[] args) {
        int[] nums = {9};
        int maxOp = 2;

        System.out.println(minimumSize(nums, maxOp));
    }
}
