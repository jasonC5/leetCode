package com.jason.jingsai.competition284;

public class Code3 {
    public static int maximumTop(int[] nums, int k) {
        if (nums.length == 1 && (k & 1) == 1) {//
            return -1;
        }
        int n = Math.min(k - 1, nums.length - 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        if (k > nums.length) {// 最大值  k >= n
            return Math.max(max, nums[nums.length - 1]);
        } else if (k == nums.length) {//前nums-1位上的最大值
            return max;
        } else {//k < nums.length
            return Math.max(max, nums[k]);
        }
    }

    public static void main(String[] args) {
        int[] nums = {91, 98, 18};
        int k = 2;
        System.out.println(maximumTop(nums, k));
        nums = new int[]{35, 43, 23, 86, 23, 45, 84, 2, 18, 83, 79, 28, 54, 81, 12, 94, 14, 0, 0, 29, 94, 12, 13, 1, 48, 85, 22, 95, 24, 5, 73, 10, 96, 97, 72, 41, 52, 1, 91, 3, 20, 22, 41, 98, 70, 20, 52, 48, 91, 84, 16, 30, 27, 35, 69, 33, 67, 18, 4, 53, 86, 78, 26, 83, 13, 96, 29, 15, 34, 80, 16, 49};
        k = 15;
        System.out.println(maximumTop(nums, k));
    }

}
