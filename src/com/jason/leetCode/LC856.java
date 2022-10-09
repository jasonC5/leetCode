package com.jason.leetCode;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/score-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/10/9 9:16:10
 * @description
 */
public class LC856 {
    public static int scoreOfParentheses(String s) {
        int n = s.length();
        int left = 1, i = 1;
        for (; i < n; i++) {
            if (left == 0) {
                break;
            }
            left += s.charAt(i) == '(' ? 1 : -1;
        }
        if (i == n) {
            return n == 2 ? 1 : scoreOfParentheses(s.substring(1, n - 1)) << 1;
        } else {
            return scoreOfParentheses(s.substring(0, i)) + scoreOfParentheses(s.substring(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("()(())"));
    }
}
