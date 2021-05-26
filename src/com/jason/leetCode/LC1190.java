package com.jason.leetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 *
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 *
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 *
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1190 {

    public static void main(String[] args) {
        String s = "(u(love)i)";
        String s1 = Solution.reverseParentheses(s);
        System.out.println(s1);
    }

    static class Solution {
//        public static String reverseParentheses(String s) {
//            int length = s.length();
//            String start = "";
//            String end = "";
//            String inner = "";
//            int point1=0,point2=length-1;//头尾指针
//            Boolean direction = true;//先正向
//            //1.先正向读取开括号前面的到start中;，2，再正向读取开括号后面的，3.最后反向读取括号内部的
//            for (int i=0;i<length;i++){
//                char c;
//                if (direction) {
//                    c = s.charAt(point1++);
//                } else {
//                    c = s.charAt(point2--);
//                }
//                if (c == '(') {
//                    direction = false;
//                } else if (c == ')') {
//                    //读取到闭括号，找里面的
//                    inner = reverseParentheses(s.substring(point1,point2-point1));
//                } else {
//                    //read
//                    if (direction) {//正向
//                        start += c;
//                    } else {//逆向
//                        end = c + end;
//                    }
//                }
//            }
//            return start + inner + end;
//        }
        //栈
        public static String reverseParentheses(String s) {
            Deque<String> stack = new LinkedList<String>();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    stack.push(sb.toString());
//                    sb.setLength(0);
                    sb = new StringBuffer();
                } else if (ch == ')') {
                    sb.reverse();
                    sb.insert(0, stack.pop());
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }
}
