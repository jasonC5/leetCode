package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

public class LC1189 {
    public static final Map<Character, Integer> INDEX;
    public static final int[] COUNT;

    static {
        INDEX = new HashMap<>();
        INDEX.put('b', 0);
        INDEX.put('a', 1);
        INDEX.put('l', 2);
        INDEX.put('o', 3);
        INDEX.put('n', 4);
        COUNT = new int[]{1, 1, 2, 2, 1};
    }

    public int maxNumberOfBalloons(String text) {
        char[] chars = text.toCharArray();
        int[] nums = new int[5];
        for (char c : chars) {
            if (INDEX.containsKey(c)) {
                nums[INDEX.get(c)]++;
            }
        }
        int ans = nums[0];
        for (int i = 0; i < 5; i++) {
            ans = Math.min(ans, nums[i] / COUNT[i]);
        }
        return ans;
    }
}
