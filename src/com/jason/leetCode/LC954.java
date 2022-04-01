package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC954 {
    public static boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            int find = num > 0 ? num / 2 : num * 2;
            if (!(Math.abs(find) < Math.abs(num) && find * 2 != num) && countMap.containsKey(find)) {
                Integer count = countMap.get(find);
                if (count == 1) {
                    countMap.remove(find);
                } else {
                    countMap.put(find, count - 1);
                }
            } else {
                Integer count = countMap.getOrDefault(num, 0);
                countMap.put(num, count + 1);
            }
        }
        return countMap.isEmpty();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 3, 6};
        System.out.println(canReorderDoubled(arr));
        arr = new int[]{2, 1, 2, 6};
        System.out.println(canReorderDoubled(arr));
        arr = new int[]{4, -2, 2, -4};
        System.out.println(canReorderDoubled(arr));
    }
}
