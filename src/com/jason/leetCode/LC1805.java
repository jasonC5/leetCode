package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/12/6 15:49:28
 * @description
 */
public class LC1805 {
    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        char[] chars = word.toCharArray();
        StringBuilder cur = new StringBuilder();
        boolean start = false;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                if (start) {
                    if (cur.length() == 0 && c == '0') {
                        continue;
                    } else {
                        cur.append(c);
                    }
                } else {
                    start = true;
                    if (c != '0') {
                        cur.append(c);
                    }
                }

            } else {
                if (start) {
                    set.add(cur.toString());
                    start = false;
                    cur = new StringBuilder(0);
                }
            }
        }
        if (start) {
            set.add(cur.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String str = "035985750011523523129774573439111590559325a1554234973";
        System.out.println(numDifferentIntegers(str));
    }
}
