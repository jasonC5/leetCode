package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/11/11 9:15:32
 * @description
 */
public class LC1704 {
    public static final Set<Character> set;

    static {
        set = new HashSet<>();
        set.add('a');
        set.add('i');
        set.add('u');
        set.add('e');
        set.add('o');
        set.add('A');
        set.add('I');
        set.add('U');
        set.add('E');
        set.add('O');
    }

    public boolean halvesAreAlike(String s) {
        int length = s.length(), cnt = 0;
        for (int i = 0; i < length >> 1; i++) {
            cnt += set.contains(s.charAt(i)) ? 1 : 0;
        }
        for (int i = length >> 1; i < length; i++) {
            cnt -= set.contains(s.charAt(i)) ? 1 : 0;
        }
        return cnt == 0;
    }
}
