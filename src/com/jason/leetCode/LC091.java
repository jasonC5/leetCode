package com.jason.leetCode;

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LC091 {

    public static void main(String[] args) {
        String s = "226";
        final int count = Solution.numDecodings(s);
        System.out.println(count);
    }

    public static class Solution {
        public static int numDecodings(String s) {
            //动态规划
            // 思路：先假设字符串是无限长度的，我们要做的是求第n位为止有多少种可能
            // 1.n=0的时候，只有1中可能，就是空字符串
            // 2.n=1的时候，只有1中可能，就是一个字符
            // 3.n=2的时候如果和前一个字符组合<26那么就有两种可能，和前一个数字组合成一个字符【P[n-2]】或者自己单独组成一个字符 【P[n-1]】   P[n-1] + P[n-2]
            //      如果当前字符为0，那么也只有一种可能和前面的数字组成10或者20--前面的数字只能是1或者2， = P[n-1]
            //      如果前一个数字为0，那么也只有一种可能，自己组成一个字符     = P[n-1]
            //……
            if (s == null || s.length() == 0 || s.equals("0")) {
                return 0;
            }
            int res[] = new int[s.length()+1];//这里从0开始，字符串从1开始
            res[0] = 1;
            for (int i = 1; i <= s.length(); i++) {
                char c_1 = s.charAt(i - 1);//s[i-1]
                if (c_1 != '0') {//如果当前字符为0，则当前字符无法翻译成一个字母，无法直接使用上一个结果
                    res[i] += res[i-1];
                }
                char c_2;
                if (i > 1 &&//>=2才有前两位
                        (c_2 = s.charAt(i - 2)) != '0' && //如果前一个字符为0，则无法和自己组成一个数字翻译成字符
                        Integer.valueOf("" + c_2+c_1) <= 26 ) {//如果组成的数字>26,也无法翻译成一个字母
                    res[i] += res[i-2];
                }
            }
            return res[s.length()];
        }
    }
}
