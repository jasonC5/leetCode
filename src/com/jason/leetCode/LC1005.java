package com.jason.leetCode;

import java.util.Arrays;

public class LC1005 {
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        //统计有多少负数
        int negative = 0;
        boolean haveZero = false;
        for (int num : nums) {
            if (num == 0) {
                haveZero = true;
            }
            if (num >= 0) {
                break;
            }
            negative++;
        }
        int min = Integer.MAX_VALUE;
        if (negative - 1 >= 0) {
            min = Math.min(min, Math.abs(nums[negative - 1]));
        }
        if (negative < nums.length) {
            min = Math.min(min, nums[negative]);
        }
        for (int i = 0; i < k && i < negative; i++) {
            nums[i] = -nums[i];
        }
        int sum = Arrays.stream(nums).sum();
        if (k <= negative || haveZero || ((negative - k) & 1) == 0) {
            return sum;
        } else {
            return sum - 2 * min;
        }
    }

    public static void main(String[] args) {
        int[] num = {5,6,9,-3,3};
        int k = 2;
        System.out.println(largestSumAfterKNegations(num, k));
    }
}
