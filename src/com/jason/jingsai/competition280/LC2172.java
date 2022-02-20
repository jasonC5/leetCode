package com.jason.jingsai.competition280;

import java.util.HashMap;
import java.util.Map;

public class LC2172 {
    public int maximumANDSum(int[] nums, int numSlots) {
        int[] bucket = new int[numSlots];
        Map<Long, Integer> cache = new HashMap<>();
        return process(nums, bucket, 0, numSlots, cache);
    }

    public int process(int[] nums, int[] bucket, int idx, int numSlots, Map<Long, Integer> cache) {//numSlots <= 9   nums.length <=18
        if (idx == nums.length) {
            return 0;
        }
        long key = idx;
        for (int i = 0; i < numSlots; i++) {
            key = key << 2 | bucket[i];
        }
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int num = 0;
        for (int i = 0; i < numSlots; i++) {
            if (bucket[i] < 2) {
                bucket[i]++;
                num = Math.max(num, ((i + 1) & nums[idx]) + process(nums, bucket, idx + 1, numSlots, cache));
                bucket[i]--;
            }
        }
        cache.put(key, num);
        return num;
    }
}
