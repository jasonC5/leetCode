package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2022/8/12 15:21:28
 * @description
 */
public class LC1282 {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        // 数量 -> 在这个数量的桶里的下标
        Map<Integer, List<Integer>> countMap = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> container = countMap.getOrDefault(groupSizes[i], new ArrayList<>());
            container.add(i);
            countMap.put(groupSizes[i], container);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer size : countMap.keySet()) {
            List<Integer> container = countMap.get(size);
            List<Integer> list = new ArrayList<>();
            for (Integer integer : container) {
                list.add(integer);
                if (list.size() == size) {
                    ans.add(list);
                    list = new ArrayList<>();
                }
            }
        }
        return ans;
    }
}
