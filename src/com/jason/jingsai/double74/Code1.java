package com.jason.jingsai.double74;

import java.util.HashSet;
import java.util.Set;

public class Code1 {
    // HashSet
    public boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.isEmpty();
    }
}
