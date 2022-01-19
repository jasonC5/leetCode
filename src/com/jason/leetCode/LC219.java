package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC219 {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 1) {
            return false;
        }
        int left = 0, right = 0;
        int length = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < Math.min(k + 1, length); i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            right++;
        }
        while (right < length) {
            set.remove(nums[left++]);
            if (set.contains(nums[right])) {
                return true;
            }
            set.add(nums[right++]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
    }
}
