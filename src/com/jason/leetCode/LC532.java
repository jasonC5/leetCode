package com.jason.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/6/16 12:20:57
 * @description
 */
public class LC532 {
    public int findPairs(int[] nums, int k) {
        Set<Integer> exist = new HashSet<>();
        Set<Integer> fond = new HashSet<>();
        for (int num : nums) {
            int tag1 = num + k;
            int tag2 = num - k;
            if (exist.contains(tag1)) {
                fond.add(num);
            }
            if (exist.contains(tag2)) {
                fond.add(num - k);
            }
            exist.add(num);
        }
        return fond.size();
    }
}
