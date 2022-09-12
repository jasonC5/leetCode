package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/9/12 9:04:56
 * @description
 */
public class LC1608 {
    public static int specialArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i <= n; i++) {
            int cur = n - i;
            if (nums[cur] >= i && (cur == 0 || nums[cur - 1] < i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3, 5};
        System.out.println(specialArray(ints));
    }

}
