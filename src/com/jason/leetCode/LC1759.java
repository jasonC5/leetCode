package com.jason.leetCode;

/**
 * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 10^9 + 7 取余 后的结果。
 * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
 * 子字符串 是字符串中的一个连续字符序列。
 *
 * @author chenjieaj
 * @date 2022/12/26 16:15:36
 * @description
 */
public class LC1759 {
    public int countHomogenous(String s) {
        long ans = 0;
        int n = s.length(), len = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                len++;
            } else {
                ans += (long) (len + 1) * len / 2;
                len = 1;
            }
        }
        ans += (long) (len + 1) * len / 2;
        return (int) (ans % 1000000007);
    }
}
