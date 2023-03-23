package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/3/23 9:35:38
 * @description
 */
public class LC1630 {
    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        int[] tmp = new int[nums.length];
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int length = r[i] - l[i] + 1;
            for (int j = 0; j < length; j++) {
                tmp[j] = nums[l[i] + j];
            }
            Arrays.sort(tmp, 0, length);
            ans.add(check(tmp, length));
        }
        return ans;
    }

    private static Boolean check(int[] tmp, int length) {
        if (length <= 2) {
            return true;
        }
        int steep = tmp[1] - tmp[0];
        for (int i = 2; i < length; i++) {
            if (tmp[i] - tmp[i - 1] != steep) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 5, 9, 3, 7};
        int[] l = {0, 0, 2};
        int[] r = {2, 3, 5};
        System.out.println(checkArithmeticSubarrays(nums, l, r));
    }
}
