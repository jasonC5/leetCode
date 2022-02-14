package com.jason.leetCode;

public class LC540 {
    //    public int singleNonDuplicate(int[] nums) {
//        int ans = 0;
//        for (int num : nums) {
//            ans ^= num;
//        }
//        return ans;
//    }
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
