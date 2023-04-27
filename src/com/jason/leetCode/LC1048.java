package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/4/27 9:24:29
 * @description
 */
public class LC1048 {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        Map<String, Integer> lengthMap = new HashMap<>();
        int ans = 0, wordLen, preLen, curLen;
        String tmp;
        for (String word : words) {
            preLen = 0;
            wordLen = word.length();
            for (int i = 0; i < wordLen; i++) {
                tmp = word.substring(0, i) + word.substring(i + 1);
                preLen = Math.max(preLen, lengthMap.getOrDefault(tmp, 0));
            }
            curLen = preLen + 1;
            lengthMap.put(word, curLen);
            ans = Math.max(ans, curLen);
        }
        return ans;
    }
}
