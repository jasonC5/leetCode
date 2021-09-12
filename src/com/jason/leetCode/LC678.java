package com.jason.leetCode;

public class LC678 {
    public static boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        int left = 0, star = 0;
        for (char c : chars) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else if (star > 0) {
                    star--;
                } else {
                    return false;
                }
            } else {
                star++;
            }
        }
//        if (left > star) {
//            return false;
//        }
        int right = 0;
        star = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '(') {
                if (right > 0) {
                    right--;
                } else if (star > 0) {
                    star--;
                } else {
                    return false;
                }
            } else if (chars[i] == ')') {
                right++;
            } else {
                star++;
            }
        }
//        return right <= star;
        return true;
    }

    public static void main(String[] args) {
        String str = "(*)";
        System.out.println(checkValidString(str));
    }
}
