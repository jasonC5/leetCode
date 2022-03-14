package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return ans;
        }
        int wordsNum = words.length;
        int wordsLength = words[0].length();
        int prefixLen = wordsNum * wordsLength;
        Map<String, Integer> countNum = new HashMap<>();
        for (String word : words) {
            countNum.put(word, countNum.getOrDefault(word, 0) + 1);
        }
        next:
        for (int i = 0; i < s.length() - prefixLen + 1; i++) {
            Map<String, Integer> curCountMap = new HashMap<>();
            for (int j = i; j < i + prefixLen; j += wordsLength) {
                String substring = s.substring(j, j + wordsLength);
                if (!countNum.containsKey(substring)) {
                    continue next;
                } else {
                    curCountMap.put(substring, curCountMap.getOrDefault(substring, 0) + 1);
                }
            }
            if (countNum.equals(curCountMap)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
