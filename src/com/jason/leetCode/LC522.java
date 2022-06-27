package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/6/27 9:14:16
 * @description
 */
public class LC522 {
    public static int findLUSlength(String[] strs) {
        int n = strs.length;
        Arrays.sort(strs, (s1, s2) -> s1.length() == s2.length() ? (s1.compareTo(s2)) : (s2.length() - s1.length()));
        for (int i = 0; i < n; i++) {
            // 能找到自己是某一个字符串的子序列
            boolean fond = false;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (fond = check(strs[i], strs[j])) {
                    break;
                }
            }
            if (!fond) {
                return strs[i].length();
            }
        }
        return -1;
    }

    // 判断s1是s2的子序列
    private static boolean check(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return false;
        }
        //双指针判断str1是否是str2的子序列
        int p = 0, q = 0;
        while (p < str1.length() && q < str2.length()) {
            if (str1.charAt(p) == str2.charAt(q)) {
                p++;
                q++;
            } else {
                q++;
            }
        }
        return p == str1.length();
    }

    public static void main(String[] args) {
        System.out.println(findLUSlength(new String[]{"aabbcc", "aabbcc", "cb", "abc", "mmnnqq"}));
    }
}
