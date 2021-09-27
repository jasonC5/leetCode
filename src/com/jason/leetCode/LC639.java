package com.jason.leetCode;

/**
 * https://leetcode-cn.com/problems/decode-ways-ii/
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" 对应分组 (1 1 10 6)
 * "KJF" 对应分组 (11 10 6)
 * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
 * <p>
 * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
 * <p>
 * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
 * <p>
 * 由于答案数目可能非常大，返回对 109 + 7 取余 的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "*"
 * 输出：9
 * 解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
 * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
 * 因此，"*" 总共有 9 种解码方法。
 * 示例 2：
 * <p>
 * 输入：s = "1*"
 * 输出：18
 * 解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
 * 每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
 * 因此，"1*" 共有 9 * 2 = 18 种解码方法。
 * 示例 3：
 * <p>
 * 输入：s = "2*"
 * 输出：15
 * 解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
 * "21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
 * 因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC639 {

    public static int numDecodings91(String s) {
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
        int res[] = new int[s.length() + 1];//这里从0开始，字符串从1开始
        res[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            char c_1 = s.charAt(i - 1);//s[i-1]
            if (c_1 != '0') {//如果当前字符为0，则当前字符无法翻译成一个字母，无法直接使用上一个结果
                res[i] += res[i - 1];
            }
            char c_2;
            if (i > 1 &&//>=2才有前两位
                    (c_2 = s.charAt(i - 2)) != '0' && //如果前一个字符为0，则无法和自己组成一个数字翻译成字符
                    Integer.parseInt("" + c_2 + c_1) <= 26) {//如果组成的数字>26,也无法翻译成一个字母
                res[i] += res[i - 2];
            }
        }
        return res[s.length()];
    }

    public static final int MOD = 1000000007;

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || "0".equals(s) || '0' == s.charAt(0)) {
            return 0;
        }
        int length = s.length();
        long dp[] = new long[length + 1];//这里从0开始，字符串从1开始
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : 1;
        for (int i = 2; i <= s.length(); i++) {
            //当前数字单独组成一个字符
            char c_1 = s.charAt(i - 1);//s[i-1]
            if (c_1 == '*') {//如果为*，则当前数字组成字符有9种可能
                dp[i] = ((dp[i - 1] * 9) % MOD + dp[i]) % MOD;
            } else if (c_1 != '0') {//如果当前字符为0，则当前字符无法翻译成一个字母，无法直接使用上一个结果
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }
            //当前数字和前一个数字，一起组成一个字符
            char c_2 = s.charAt(i - 2);
            if (c_2 == '1') {
                if (c_1 == '*') {
                    dp[i] = ((dp[i - 2] * 9) % MOD + dp[i]) % MOD;
                } else {
                    dp[i] = (dp[i] + dp[i - 2]) % MOD;
                }
            } else if (c_2 == '2') {
                if (c_1 == '*') {
                    dp[i] = ((dp[i - 2] * 6) % MOD + dp[i]) % MOD;
                } else if (c_1 == '7' || c_1 == '8' || c_1 == '9') {
                    //不符合，不能两个合成一个字符
                } else {//0~6
                    dp[i] = (dp[i] + dp[i - 2]) % MOD;
                }
            } else if (c_2 == '*') {
                if (c_1 == '*') {
                    //c_2当成1的时候，
                    dp[i] = ((dp[i - 2] * 9) % MOD + dp[i]) % MOD;
                    //c_2当成2的时候
                    dp[i] = ((dp[i - 2] * 6) % MOD + dp[i]) % MOD;
                } else if (c_1 == '7' || c_1 == '8' || c_1 == '9') {//7 8 9
                    //c_2当成1的时候【只能是1】
                    dp[i] = (dp[i] + dp[i - 2]) % MOD;
                } else {//0~6
                    //c_2当成1的时候，
                    dp[i] = (dp[i] + dp[i - 2]) % MOD;
                    //c_2当成2的时候
                    dp[i] = (dp[i] + dp[i - 2]) % MOD;
                }
            }
        }
        return (int)dp[length];
    }

    public static int numDecodings2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long[] f = new long[n];
        f[0] = cs[0] == '*' ? 9 : (cs[0] != '0' ? 1 : 0);
        for (int i = 1; i < n; i++) {
            char c = cs[i], prev = cs[i - 1];
            if (c == '*') {
                // cs[i] 单独作为一个 item
                f[i] += f[i - 1] * 9;
                // cs[i] 与前一个字符共同作为一个 item
                if (prev == '*') {
                    // 11 - 19 & 21 - 26
                    f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 15;
                } else {
                    int u = (int)(prev - '0');
                    if (u == 1) {
                        f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 9;
                    } else if (u == 2) {
                        f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 6;
                    }
                }
            } else {
                int t = (int)(c - '0');
                if (prev == '*') {
                    if (t == 0) {
                        f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 2;
                    } else {
                        // cs[i] 单独作为一个 item
                        f[i] += f[i - 1];
                        // cs[i] 与前一个字符共同作为一个 item
                        if (t <= 6) {
                            f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 2;
                        } else {
                            f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                        }
                    }
                } else {
                    int u = (int)(prev - '0');
                    if (t == 0) {
                        if (u == 1 || u == 2) {
                            f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                        }
                    } else {
                        // cs[i] 单独作为一个 item
                        f[i] += (f[i - 1]);
                        // cs[i] 与前一个字符共同作为一个 item
                        if (u == 1) {
                            f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                        } else if (u == 2 && t <= 6) {
                            f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                        }
                    }
                }
            }
            f[i] %= MOD;
        }
        return (int)(f[n - 1]);
    }

    public static void main(String[] args) {
        //"7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"
        String str = "7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*";
        System.out.println(Integer.MAX_VALUE);
        System.out.println(numDecodings(str));
        System.out.println(numDecodings2(str));
    }

}
