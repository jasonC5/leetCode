package com.jason.jingsai.double73;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author JasonC5
 */
public class Code1 {
    public int mostFrequent(int[] nums, int key) {
        boolean pre = false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (pre) {
                Integer count = map.getOrDefault(num, 0);
                map.put(num, count + 1);
            }
            pre = num == key;
        }
        int ans = -1, max = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max){
                max = entry.getValue();
                ans = entry.getKey();
            }
        }
        return ans;
    }
}
