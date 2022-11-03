package com.jason.leetCode;

/**
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 * <p>
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 * <p>
 * https://leetcode.cn/problems/maximum-repeating-substring/
 *
 * @author chenjieaj
 * @date 2022/11/3 8:53:08
 * @description
 */
public class LC1668 {
    public static int maxRepeating(String sequence, String word) {
        String cur = word;
        int k = 0;
        while (sequence.contains(cur)) {
            k++;
            cur += word;
        }
        return k;
    }

    public static void main(String[] args) {
    }
}
