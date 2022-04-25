package com.jason.leetCode;

import java.util.*;

/**
 * @author Administrator
 */
public class LC398 {
    class Solution {
        Map<Integer, List<Integer>> idxMap;
        Random random;

        public Solution(int[] nums) {
            idxMap = new HashMap<>();
            random = new Random();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> idxs = idxMap.getOrDefault(nums[i], new ArrayList<>());
                idxs.add(i);
                idxMap.put(nums[i], idxs);
            }
        }

        public int pick(int target) {
            List<Integer> idxs = idxMap.get(target);
            int idx = random.nextInt(idxs.size());
            return idxs.get(idx);
        }
    }
}
