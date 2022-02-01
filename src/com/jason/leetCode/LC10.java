package com.jason.leetCode;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC10 {
    public static boolean isMatch(String s, String p) {
        char[] scs = s.toCharArray();
        char[] pcs = p.toCharArray();
        int si = 0, pi = 0;
        return check(scs, pcs, si, pi);
    }

    private static boolean check(char[] scs, char[] pcs, int si, int pi) {
        if (si == scs.length && pi == pcs.length) {
            return true;
        } else if (pi == pcs.length) {
            return false;
        } else if (si == scs.length) {
            //如果后面都是 x* 这种，也为true
            if (pi < pcs.length - 1 && pcs[pi + 1] == '*') {
                return check(scs, pcs, si, pi + 2);
            } else if (pi < pcs.length && pcs[pi] == '*') {
                return check(scs, pcs, si, pi + 1);
            }
            return false;
        }
        if (scs[si] == pcs[pi] || pcs[pi] == '.') {
            boolean p1 = check(scs, pcs, si + 1, pi + 1);
            boolean p2 = false;
            if (pi < pcs.length - 1 && pcs[pi + 1] == '*') {
                p2 = check(scs, pcs, si, pi + 2);
            }
            return p1 || p2;
        } else {
            if (pcs[pi] == '*') {//0个或多个前面的字符
                boolean p1 = check(scs, pcs, si, pi + 1);//不匹配，0个
                boolean p2 = false;
                boolean p3 = false;
                if (pcs[pi - 1] == scs[si] || pcs[pi - 1] == '.') {
                    p2 = check(scs, pcs, si + 1, pi + 1);//匹配一个
                    p3 = check(scs, pcs, si + 1, pi);//匹配多个
                }
                return p1 || p2 || p3;
            } else if (pi < pcs.length - 1 && pcs[pi + 1] == '*') {//后面是* 可以删掉前面的
                return check(scs, pcs, si, pi + 2);
            }
            return false;
        }
    }

    public static void main(String[] args) {
//        String s = "aab", p = "c*a*b";
//        String s = "mississippi", p = "mis*is*p*.";
//        String s = "a", p = "ab*";
        String s = "bcbabcaacacbcabac",
                p = "a*c*a*b*.*aa*c*a*a*";
        System.out.println(isMatch(s, p));
    }
    //动态规划
//    public static boolean isMatch2(String s, String p) {
//        char[] scs = s.toCharArray();
//        char[] pcs = p.toCharArray();
//        int si = 0, pi = 0;
//
////        return check2(scs, pcs, si, pi);
//    }
}
