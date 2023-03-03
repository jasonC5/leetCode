package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/3/3 9:35:21
 * @description
 */
public class LC1487 {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        int n = names.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(names[i])) {
                ans[i] = names[i];
                map.put(names[i], 1);
            } else {
                int k = map.get(names[i]);
                String name = names[i] + "(" + k + ")";
                while (map.containsKey(name)) {
                    k++;
                    name = names[i] + "(" + k + ")";
                }
                ans[i] = name;
                map.put(name, 1);
                map.put(names[i], k + 1);
            }
        }
        return ans;
    }
}
