package com.jason.jingsai.competition277;

import java.util.Arrays;

public class C5991 {
    public static int[] rearrangeArray(int[] nums) {
        int length = nums.length;
        int[] positives = new int[length >> 1];
        int[] negatives = new int[length >> 1];
        int pp = 0, np = 0;
        for (int num : nums) {
            if (num < 0) {
                negatives[np++] = num;
            } else {
                positives[pp++] = num;
            }
        }
        for (int i = 0; i < length; i++) {
            if ((i & 1) == 0) {
                nums[i] = positives[i >> 1];
            } else {
                nums[i] = negatives[i >> 1];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, -2, -5, 2, -4};
        System.out.println(Arrays.toString(rearrangeArray(a)));
    }
}
