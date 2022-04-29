package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class LC905 {
    public static int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[left] & 1) == 1) {// 奇数
                swap(nums, left, right--);
            } else {
                left++;
            }

        }
        return nums;
    }

    private static void swap(int[] nums, int left, int right) {
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParity(new int[]{3, 1, 2, 4})));
        System.out.println(Arrays.toString(sortArrayByParity(new int[]{1})));
    }
}
