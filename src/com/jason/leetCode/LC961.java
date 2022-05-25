package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC961 {
    public static int repeatedNTimes(int[] nums) {
        int count = 1, delegate = nums[0], length = nums.length;
        for (int i = 1; i < length; i++) {
            if (count == 0) {
                delegate = nums[i];
                count++;
            } else if (delegate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        if (count > 0) {
            return delegate;
        }
        // count == 0 可能是delegate，也可能是nums[length-1]
        for (int num : nums) {
            count += (num == delegate ? 1 : 0);
        }
        return count == (length >> 1) ? delegate : nums[length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 6, 9};
        System.out.println(repeatedNTimes(nums));
    }
}
