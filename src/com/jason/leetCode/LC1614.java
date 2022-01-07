package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC1614 {
    public int maxDepth(String s) {
        int deep = 0, maxDeep = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                deep++;
                maxDeep = Math.max(deep, maxDeep);
            } else if (c == ')') {
                deep--;
            }
        }
        return maxDeep;
    }
}
