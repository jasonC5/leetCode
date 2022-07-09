package com.jason.leetCode;

public class LC1201 {
    public String removeOuterParentheses(String s) {
        int left = 0, leftCount = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            leftCount += s.charAt(i) == '(' ? 1 : -1;
            if (leftCount == 0) {// 一个原语
                if (i - left > 1) {
                    ans.append(s.substring(left+1, i));
                }
                left = i + 1;
                leftCount = 0;
            }
        }
        return ans.toString();
    }
}
