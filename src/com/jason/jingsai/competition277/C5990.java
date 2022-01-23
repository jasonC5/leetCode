package com.jason.jingsai.competition277;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C5990 {
    public static List<Integer> findLonely(int[] nums) {
        int length = nums.length;
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length == 1 || nums[0] < nums[1] - 1) {
            ans.add(nums[0]);
        }
        if (nums.length > 1 && nums[length - 1] > nums[length - 2] + 1) {
            ans.add(nums[length - 1]);
        }
        for (int i = 1; i < length - 1; i++) {
            if (nums[i] > nums[i - 1] + 1 && nums[i] < nums[i + 1] - 1) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {10, 6, 5, 8};
        System.out.println(findLonely(a));
    }
}
