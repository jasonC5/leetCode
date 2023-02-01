package com.jason.leetCode;

import java.util.HashMap;

/**
 * @author chenjieaj
 * @date 2023/2/1 14:17:35
 * @description
 */
public class LC2325 {
    public String decodeMessage(String key, String message) {
        HashMap<Character, Character> map = new HashMap<>();
        for (char c : key.toCharArray()) {
            if (!map.containsKey(c) && c != ' ') {
                map.put(c, (char) (map.size() + 'a'));
            }
            if (map.size() == 26) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray()) {
            sb.append(map.getOrDefault(c, ' '));
        }
        return sb.toString();
    }
}
