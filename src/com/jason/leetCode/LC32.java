package com.jason.leetCode;

import java.util.Stack;

/**
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC32 {

    //模拟法，边界太多
    public static int longestValidParentheses2(String s) {
        boolean start = false;
        int openCnt = 0;
        int closeCnt = 0;

        int maxCount = 0;
        int checkCount = 0;
        int preCount = 0;//当开启新的（ 开括号的时候，记录之前的count，当闭括号消耗掉所有的开括号的时候，需要加上preCount
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == ')') {
                if (!start) {
                    continue;
                }
                if (openCnt <= 0) {
                    maxCount = Math.max(maxCount, checkCount);
                    checkCount = 0;
                    //之前的括号被闭括号隔开了，
                    preCount = 0;
//                            openCnt = -1;
//                            closeCnt = 0;
                } else {
                    checkCount += 2;
                    openCnt--;
                    if (openCnt == 0) {
                        //把所有的开括号消耗完了，加上之前的数量
                        checkCount += preCount;
                    }
                    //每次都比较一下，
                    maxCount = Math.max(maxCount, checkCount);
                }
            } else {
                if (!start) {
                    start = true;
                }
                if (openCnt < 0) {
                    openCnt = 1;
                } else if (openCnt == 0) {
                    //之前已经把所有的开括号消耗完了，这个是新的开括号，
                    preCount = checkCount;
                    checkCount = 0;
                    openCnt++;
                } else {
                    openCnt++;
                }
            }
        }
        return maxCount;
    }

    //用栈来实现，开括号吖栈，闭括号出栈
    public static int longestValidParentheses3(String s) {
        int maxCount = 0;
        Stack<Integer> stack = new Stack();
        char[] charArray = s.toCharArray();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (charArray[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxCount = Math.max(maxCount, i - stack.peek());
                }
            }
        }
        return maxCount;
    }


    public static void main(String[] args) {
        String s = "(()()()()";
        int i = longestValidParentheses(s);
        System.out.println(i);
    }

    private static int longestValidParentheses(String s) {
        //动态规划

        return 0;
    }
}
