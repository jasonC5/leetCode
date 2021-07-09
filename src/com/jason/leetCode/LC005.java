package com.jason.leetCode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC005 {

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindromeDp(s));
        System.out.println(longestPalindromeDp2(s));
    }

    public static class Mark {
        boolean isPalindrome;
        String longestPalindrome;
        int length;

        public Mark(boolean isPalindrome, String longestPalindrome, int length) {
            this.isPalindrome = isPalindrome;
            this.longestPalindrome = longestPalindrome;
            this.length = length;
        }
    }

    public static String longestPalindrome(String s) {
        //最长回文子串
        char[] chars = s.toCharArray();
        Mark mark = process(chars, 0, chars.length - 1);
        return mark.longestPalindrome;
    }

    private static Mark process(char[] chars, int left, int right) {
        if (left == right) {
            return new Mark(true, String.valueOf(chars[left]), 1);
        } else if (left > right) {
            return new Mark(true, "", 0);
        }
        Mark p3 = process(chars, left + 1, right - 1);
        if (p3.isPalindrome && chars[left] == chars[right]) {
            return new Mark(true, chars[left] + p3.longestPalindrome + chars[right], p3.length + 2);
        } else {
            Mark p1 = process(chars, left + 1, right);
            Mark p2 = process(chars, left, right - 1);
            Mark max = p1.length >= p2.length ? p1 : p2;
            return new Mark(false, max.longestPalindrome, max.length);
        }
    }

    //动态规划1
    public static String longestPalindromeDp(String s) {
        //最长回文子串
        char[] chars = s.toCharArray();
        int length = chars.length;
        Mark[][] dp = new Mark[length][length];
        dp[0][0] = new Mark(true, String.valueOf(chars[0]), 1);
        for (int i = 1; i < length; i++) {
            dp[i][i] = new Mark(true, String.valueOf(chars[i]), 1);
            dp[i][i - 1] = new Mark(true, "", 0);
        }
        for (int left = length - 2; left >= 0; left--) {
            for (int right = left + 1; right < length; right++) {
                Mark p3 = dp[left + 1][right - 1];
                if (p3.isPalindrome && chars[left] == chars[right]) {
                    dp[left][right] = new Mark(true, chars[left] + p3.longestPalindrome + chars[right], p3.length + 2);
                } else {
                    Mark p1 = dp[left + 1][right];
                    Mark p2 = dp[left][right - 1];
                    Mark max = p1.length >= p2.length ? p1 : p2;
                    dp[left][right] = new Mark(false, max.longestPalindrome, max.length);
                }
            }
        }
        return dp[0][length - 1].longestPalindrome;
    }

    //动态规划2，只判断是否是回文子串
    public static String longestPalindromeDp2(String s) {
        int start = 0;
        int maxLen = 1;
        //最长回文子串
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean[][] dp = new boolean[length][length];
        dp[0][0] = true;
        for (int i = 1; i < length; i++) {
            dp[i][i] = true;
            dp[i][i - 1] = true;
        }
        for (int left = length - 2; left >= 0; left--) {
            for (int right = left + 1; right < length; right++) {
                boolean p3 = dp[left + 1][right - 1];
                if (p3 && chars[left] == chars[right]) {
                    dp[left][right] = true;
                    int len = right - left + 1;
                    if (len > maxLen) {
                        start = left;
                        maxLen = len;
                    }
                } else {
                    dp[left][right] = false;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

}
