package com.jason.leetCode;

/**
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * <p>
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-nice-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1763 {
    public String longestNiceSubstring(String s) {
        String ans = "";
        int maxLen = 0;
        int length = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            // 从i位置开始，往后哪个子串是
            int lower = 0, upper = 0;
            for (int j = i; j < length; j++) {
                if (Character.isLowerCase(chars[j])) {
                    lower |= 1 << (chars[j] - 'a');
                } else {
                    upper |= 1 << (chars[j] - 'A');
                }
                if (lower == upper && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
