package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class LC318 {

    public int maxProduct(String[] words) {
        int[] marks = new int[words.length];// bitmap，用每一位代表是否出现过字母
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int mark = 0;
            for (char c : chars) {
                mark |= 1 << (c - 'a');
            }
            marks[i] = mark;
        }
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((marks[i] & marks[j]) == 0) {// 说明没有公共字母
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
