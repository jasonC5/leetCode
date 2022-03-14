package com.jason.leetCode;

import java.util.TreeSet;

public class LC16 {
    public static int threeSumClosest(int[] nums, int target) {
        TreeSet<Integer> sortedTable = new TreeSet<>();
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 1; i < nums.length; i++) {
            Integer ceiling = sortedTable.ceiling(target - nums[i]);
            if (ceiling != null && Math.abs(target - ceiling - nums[i]) < Math.abs(target - ans)) {
                ans = ceiling + nums[i];
            }
            Integer floor = sortedTable.floor(target - nums[i]);
            if (floor != null && Math.abs(target - floor - nums[i]) < Math.abs(target - ans)) {
                ans = floor + nums[i];
            }
            if (ans == target) {
                return target;
            }
            for (int j = 0; j < i; j++) {// 两数之和
                sortedTable.add(nums[i] + nums[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
