package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/6/21 9:06:31
 * @description
 */
public class LC1108 {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        char[] chars = address.toCharArray();
        for (char c : chars) {
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
