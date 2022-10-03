package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/1 19:29:17
 * @description
 */
public class LC1694 {
    public String reformatNumber(String number) {
        String str = number.replace(" ", "").replace("-", "");
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i += 3) {
            if (sb.length() != 0) {
                sb.append("-");
            }
            if (i + 5 > length) {
                if (i + 3 >= length) {
                    sb.append(str.substring(i));
                } else {
                    sb.append(str.substring(i, i + 2)).append("-").append(str.substring(i + 2));
                }
                return sb.toString();
            }
            sb.append(str.substring(i, i + 3));
        }
        return sb.toString();
    }
}
