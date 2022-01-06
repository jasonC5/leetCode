package com.jason.leetCode;

public class LC1576 {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        char pre = 'a' - 1;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '?') {
                chars[i] = pre == 'z' ? 'a' : (char) (pre + 1);
                if (i != s.length() - 1 && chars[i] == chars[i + 1]) {
                    chars[i] = chars[i] == 'z' ? 'a' : (char) (chars[i] + 1);
                }
            }
            pre = chars[i];
        }
        return new String(chars);
    }
}
