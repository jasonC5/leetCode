package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n   代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(1));
    }

    public static List<String> generateParenthesis(int n) {//剩余
        List<String> ans = new ArrayList<>();
        char[] tmp = new char[2 * n];
        process(n, 0, 0, tmp, ans);
        return ans;
    }

    private static void process(int leftSurplus, int rightSurplus, int index, char[] tmp, List<String> ans) {
        if (index == tmp.length) {
            ans.add(new String(tmp));
            return;
        }
        if (leftSurplus > 0) {
            tmp[index] = '(';
            process(leftSurplus - 1, rightSurplus + 1, index + 1, tmp, ans);
        }
        if (rightSurplus > 0) {
            tmp[index] = ')';
            process(leftSurplus, rightSurplus - 1, index + 1, tmp, ans);
        }
    }

}
