package com.jason.leetCode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'  的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例  2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例  3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例  4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例  5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC20 {

    public static void main(String[] args) {
        String str = "){";
        System.out.println(isValid(str));
    }

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else if ((s.length() & 1) == 1) {
            return false;
        }
        char[] chars = s.toCharArray();
        //栈
        char[] stack = new char[chars.length];
        int size = 0;
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                //入栈
                stack[size++] = c == '(' ? ')' : (c == '[' ? ']' : '}');
            } else {
                //出栈
                if (size == 0 || c != stack[--size]) {
                    return false;
                }
            }
        }
        return size == 0;
    }
}
