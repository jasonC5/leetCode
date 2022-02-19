package com.jason.jingsai.double72;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code1 {
    public int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }
        int ans = 0;
        for (List<Integer> value : map.values()) {
            int size = value.size();
            if (size == 1) {
                continue;
            }
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if ((value.get(i) * value.get(j)) % k == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 2, 2, 1, 3};
        System.out.println(new Code1().countPairs(nums, 2));

    }
}
