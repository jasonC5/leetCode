package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        String delegate = map(pattern);
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (delegate.equals(map(word))) {
                ans.add(word);
            }
        }
        return ans;
    }

    private String map(String str) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                ans.append(map.get(c)).append("_");
            } else {
                map.put(c, i);
                ans.append(i).append("_");
            }
        }
        return ans.toString();
    }
}
