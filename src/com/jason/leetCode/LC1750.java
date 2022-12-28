package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/28 9:40:55
 * @description
 */
public class LC1750 {
    public static int minimumLength(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left <= right) {
            if (left == right || s.charAt(left) != s.charAt(right)) {
                return right - left + 1;
            }
            char c = s.charAt(left);
            while (left <= right && c == s.charAt(left)) {
                left++;
            }
            while (left <= right && c == s.charAt(right)) {
                right--;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(minimumLength("bbbbbbbbbbbbb"));
    }
}
