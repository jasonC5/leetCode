package com.jason.jingsai.competition278;

import java.util.HashSet;
import java.util.Set;

public class C5993 {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(original)) {
            original <<= 1;
        }
        return original;
    }
}
