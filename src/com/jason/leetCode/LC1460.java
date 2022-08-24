package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/8/24 9:23:59
 * @description
 */
public class LC1460 {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> ctn1 = getCount(target);
        Map<Integer, Integer> ctn2 = getCount(arr);
        if (ctn1.size() != ctn2.size()) {
            return false;
        }
        for (Integer i : ctn1.keySet()) {
            if (!ctn2.containsKey(i) || !ctn1.get(i).equals(ctn2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Integer> getCount(int[] arr) {
        Map<Integer, Integer> ctn = new HashMap<>();
        for (int i : arr) {
            Integer c = ctn.getOrDefault(i, 0);
            ctn.put(i, c + 1);
        }
        return ctn;
    }
}
