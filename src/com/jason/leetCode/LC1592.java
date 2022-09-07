package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/7 9:02:12
 * @description
 */
public class LC1592 {
    public static String reorderSpaces(String text) {
        int n = text.length();
        int spaceCount = 0, start = text.startsWith(" ") ? -1 : 0;
        int wordCount = text.startsWith(" ") ? 0 : 1;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (flag && text.charAt(i) != ' ') {
                wordCount++;
                if (start == -1) {
                    start = i;
                }
            }
            flag = text.charAt(i) == ' ';
            spaceCount += flag ? 1 : 0;
        }
        int cnt = wordCount == 1 ? 0 : spaceCount / (wordCount - 1);
        int tail = wordCount == 1 ? spaceCount : spaceCount - cnt * (wordCount - 1);
        StringBuilder ans = new StringBuilder();
        flag = false;
        for (int i = start; i < n; i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                flag = true;
                continue;
            }
            if (flag) {
                for (int k = 0; k < cnt; k++) {
                    ans.append(' ');
                }
                flag = false;
            }
            ans.append(text.charAt(i));
        }
        for (int i = 0; i < tail; i++) {
            ans.append(' ');
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "hello   world";
        System.out.println(reorderSpaces(s));
    }
}
