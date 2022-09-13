package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/9/13 9:02:41
 * @description
 */
public class LC670 {
    public static int maximumSwap(int num) {
        String str = num + "";
        char[] chars = str.toCharArray();
        int n = chars.length;
        int[] swapIdx = new int[n];
        swapIdx[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            swapIdx[i] = chars[i] > chars[swapIdx[i + 1]] ? i : swapIdx[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (swapIdx[i] != i && chars[i] != chars[swapIdx[i]]) {
                char c = chars[i];
                chars[i] = chars[swapIdx[i]];
                chars[swapIdx[i]] = c;
                return new Integer(new String(chars));
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int num = 1993;
        System.out.println(maximumSwap(num));
    }
}
