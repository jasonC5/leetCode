package com.jason.leetCode;

import java.util.*;

/**
 * @author JasonC5
 */
public class LC229 {
    public List<Integer> majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return Collections.singletonList(nums[0]);
        }
        int flag = length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > flag) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}
