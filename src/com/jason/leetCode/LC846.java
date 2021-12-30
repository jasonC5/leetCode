package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC846 {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int length = hand.length;
        if (length % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : hand) {
            Integer count = countMap.getOrDefault(num, 0);
            countMap.put(num, ++count);
        }
        for (int num : hand) {
            // 已经被使用光了
            if (!countMap.containsKey(num)) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                if (!countMap.containsKey(num + i)) {
                    return false;
                }
                Integer count = countMap.get(num + i) - 1;
                if (count == 0) {
                    countMap.remove(num + i);
                } else {
                    countMap.put(num + i, count);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,6,2,3,4,7,8};
        int gs = 3;
        System.out.println(isNStraightHand(nums, gs));
    }
}
