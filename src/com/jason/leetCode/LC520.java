package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC520 {

    public static void main(String[] args) {
        String word = "USa";
        System.out.println(detectCapitalUse(word));
    }

    public static boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        char[] chars = word.toCharArray();
        if (chars[0] < 'a') {// 首字母大写
            // 首字母大写，后面要么全部大写，要么全部小写
            boolean flag = chars[1] < 'a';
            for (int i = 2; i < chars.length; i++) {
                if (chars[i] < 'a' != flag) {
                    return false;
                }
            }
        } else {
            // 首字母小写，后面只能小写
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] < 'a') {
                    return false;
                }
            }
        }
        return true;
    }


}
