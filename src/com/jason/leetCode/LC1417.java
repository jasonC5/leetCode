package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/8/11 9:10:26
 * @description
 */
public class LC1417 {
    public String reformat(String s) {
        char[] chars = s.toCharArray();
        List<Character> numList = new ArrayList<>();
        List<Character> letterList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                letterList.add(chars[i]);
            } else {
                numList.add(chars[i]);
            }
        }
        if (Math.abs(letterList.size() - numList.size()) > 1) {
            return "";
        }
        List<Character> more = letterList.size() > numList.size() ? letterList : numList;
        List<Character> less = more == letterList ? numList : letterList;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < less.size(); i++) {
            sb.append(more.get(i));
            sb.append(less.get(i));
        }
        if ((s.length() & 1) == 1) {
            sb.append(more.get(more.size() - 1));
        }
        return sb.toString();
    }
}
