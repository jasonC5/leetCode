package com.jason.leetCode;

public class LC345 {

    public static void main(String[] args) {
        System.out.println(reverseVowels1("leetcode"));
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int flipCount = 0;
        int[] flipList = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u' ||
                    chars[i] == 'A' || chars[i] == 'E' || chars[i] == 'I' || chars[i] == 'O' || chars[i] == 'U') {
                flipList[flipCount++] = i;
            }
        }
        for (int i = 0; i < (flipCount / 2); i++) {
            char tmp = chars[flipList[i]];
            chars[flipList[i]] = chars[flipList[flipCount - i - 1]];
            chars[flipList[flipCount - i - 1]] = tmp;
        }
        return new String(chars);
    }

    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        boolean leftCheck = false, rightCheck = false;
        while (left < right) {
            leftCheck = false;
            rightCheck = false;
            if (!(leftCheck = check(chars, left))) {
                left++;
            }
            if (!(rightCheck = check(chars, right))) {
                right--;
            }
            if (leftCheck && rightCheck) {
                char tmp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = tmp;
            }
        }
        return new String(chars);
    }

    private static boolean check(char[] chars, int i) {
        return chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u' ||
                chars[i] == 'A' || chars[i] == 'E' || chars[i] == 'I' || chars[i] == 'O' || chars[i] == 'U';
    }

}
