package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LC334 {
    //贪心，错误
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int count = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                if (++count == 2) {
                    return true;
                }
                max = nums[i];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {20, 100, 10, 12, 5, 13};
        System.out.println(increasingTriplet1(nums));
    }

    public static boolean increasingTriplet1(int[] nums) {
        int length = nums.length;
        int[] leftMin = new int[length];
        int[] rightMax = new int[length];
        leftMin[0] = nums[0];
        rightMax[length - 1] = nums[length - 1];
        for (int i = 1; i < length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
            rightMax[length - 1 - i] = Math.max(rightMax[length - i], nums[length - 1 - i]);
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] > leftMin[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }
        return false;
    }
}
