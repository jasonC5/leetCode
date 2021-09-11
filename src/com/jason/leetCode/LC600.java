package com.jason.leetCode;

/**
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: 5
 * 解释:
 * 下面是带有相应二进制表示的非负整数<= 5：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC600 {

    public static int findIntegers(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        //二进制有几位的时候，有多少不含连续1的整数
        int[] dp = new int[32];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < 32; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            if (((1 << i) & n) != 0) {//第i位上有1
                ans += dp[i];
                //如果下一位还是1，提现返回
                if (i != 0 && ((1 << (i - 1)) & n) != 0) {
                    return ans + dp[i - 1];
                }
            }
        }
        return ans + 1/*一路上都没有连续的1，自己就是答案之一*/;
    }

    public static void main(String[] args) {
        System.out.println(findIntegers(5));
    }
}
