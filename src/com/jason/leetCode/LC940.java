package com.jason.leetCode;

/**
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * <p>
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 *
 * @author chenjieaj
 * @date 2022/10/14 9:19:19
 * @description
 */
public class LC940 {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        // 以'a'+i结尾的，有多少种
        int[] dp = new int[26];
        int n = s.length(), all = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int old = all;
            all = ((all << 1) % MOD - dp[c - 'a'] + MOD) % MOD;
            dp[c - 'a'] = old;
        }
        return all - 1;
    }
}
