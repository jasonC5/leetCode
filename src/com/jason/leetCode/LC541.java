package com.jason.leetCode;

import java.util.Stack;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC541 {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 2));
        System.out.println(reverseStr2("abcdefg", 2));
    }


    public static String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int count = 0, pointer = 0;
        for (int i = 0; i < chars.length; i++) {
            if (++count == k * 2) {
                count = 0;
                flip(chars, pointer, pointer + k - 1);
                pointer += 2 * k;
            }
        }
        if (pointer < chars.length) {
            flip(chars, pointer, Math.min(pointer + k - 1, chars.length - 1));
        }
        return new String(chars);
    }

    private static void flip(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = tmp;
        }
    }

    public static String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int count = 0, write = 0;
        for (int i = 0; i < chars.length; i++) {
            if (count < k) {
                stack.push(chars[i]);
            } else if (count == k) {
                while (!stack.isEmpty()) {
                    chars[write++] = stack.pop();
                }
                chars[write++] = chars[i];
            } else if (count < 2 * k) {
                chars[write++] = chars[i];
            }
            if (++count == 2 * k) {
                count = 0;
            }
        }
        while (!stack.isEmpty()) {
            chars[write++] = stack.pop();
        }
        return new String(chars);
    }
}
