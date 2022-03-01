package com.jason.leetCode;

import java.util.Arrays;

public class LC280 {
    public static void wiggleSort(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int[] newArr = Arrays.copyOf(nums, length);
        int left = 0, right = length - 1;
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            nums[i] = flag ? newArr[left++] : newArr[right--];
            flag = !flag;
        }
    }

    public static void main(String[] args) {

    }

}
