package com.jason.jingsai.double73;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonC5
 */
public class Code4 {
    public static int minMovesToMakePalindrome(String s) {
        int length = s.length();
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(length - 1)) {//头部与尾部匹配
                return i + minMovesToMakePalindrome(s.substring(0, i) + s.substring(i + 1, length - 1));
            } else if (s.charAt(length - 1 - i) == s.charAt(0)) {//尾部与头部匹配
                return i + minMovesToMakePalindrome(s.substring(1, length - i - 1) + s.substring(length - i, length));
            }
        }
        return 0;
    }

    public int minMovesToMakePalindrome1(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(s.length() - 1)) {
                return i + minMovesToMakePalindrome1(s.substring(0, i) + s.substring(i + 1, s.length() - 1));
            } else if (s.charAt(s.length() - 1 - i) == s.charAt(0)) {
                return i + minMovesToMakePalindrome1(s.substring(1, s.length() - 1 - i) + s.substring(s.length() - i));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String s = "eqvvhtcsaaqtqesvvqch";
        System.out.println(minMovesToMakePalindrome(s));
    }

}
