package com.jason.leetCode;

public class LC553 {
    public static String optimalDivision(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] + "";
        } else if (length == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(");
        for (int i = 1; i < length - 1; i++) {
            sb.append(nums[i]).append("/");
        }
        sb.append(nums[length - 1]).append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1000, 100, 10, 2};
        System.out.println(optimalDivision(nums));
    }
}
