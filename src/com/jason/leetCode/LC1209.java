package com.jason.leetCode;

import java.util.Stack;

public class LC1209 {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> count = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count.push(1);
            } else {
                Integer pop = count.pop();
                if (pop + 1 == k) {
                    sb.delete(i - k + 1, i + 1);
                    i -= k;
                } else {
                    count.push(pop + 1);
                }
            }
        }
        return sb.toString();
    }
}
