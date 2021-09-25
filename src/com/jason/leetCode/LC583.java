package com.jason.leetCode;

/**
 * 给定两个单词word1和word2，找到使得word1和word2相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC583 {

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        } else if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        return process(chars1, chars2, chars1.length, chars2.length);
    }

    //chars1从[0~index1)，chars2从[0~index2)，总共需要删多少次才能相等
    private static int process(char[] chars1, char[] chars2, int index1, int index2) {
        if (index1 == 0) {//
            return index2;
        } else if (index2 == 0) {
            return index1;
        }
        //chars1.length >= index1 >= 1，chars2.length >= index2 >= 1
        int p1 = 1 + process(chars1, chars2, index1 - 1, index2);
        int p2 = 1 + process(chars1, chars2, index1, index2 - 1);
        int p3 = Integer.MAX_VALUE;
        if (chars1[index1 - 1] == chars2[index2 - 1]) {
            p3 = process(chars1, chars2, index1 - 1, index2 - 1);
        }
        return Math.min(Math.min(p1, p2), p3);
    }


    public static int dp(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        } else if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        for (int index1 = 1; index1 <= chars1.length; index1++) {
            dp[index1][0] = index1;
        }
        for (int index2 = 1; index2 <= chars2.length; index2++) {
            dp[0][index2] = index2;
        }
        for (int index1 = 1; index1 <= chars1.length; index1++) {
            for (int index2 = 1; index2 <= chars2.length; index2++) {
                int p1 = 1 + dp[index1 - 1][index2];
                int p2 = 1 + dp[index1][index2 - 1];
                int p3 = Integer.MAX_VALUE;
                if (chars1[index1 - 1] == chars2[index2 - 1]) {
                    p3 = dp[index1 - 1][index2 - 1];
                }
                dp[index1][index2] = Math.min(Math.min(p1, p2), p3);
            }
        }
        return dp[chars1.length][chars2.length];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int times = 10000;
        System.out.println("start");
        for (int i = 0; i < times; i++) {
            String s1 = randomStr(maxLen);
            String s2 = randomStr(maxLen);
            int ans1 = minDistance(s1, s2);
            int ans2 = dp(s1, s2);
            if (ans1 != ans2) {
                System.out.println("S1:"+s1);
                System.out.println("S2:"+s2);
                System.out.println("ans1:"+ans1);
                System.out.println("ans2:"+ans2);
                System.out.println("error");
                return;
            }
        }
        System.out.println("finished");
    }

    private static String randomStr(int maxLen) {
        int len = (int)(Math.random() * maxLen) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = (char)('a' + (int)(Math.random() * 26));
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
