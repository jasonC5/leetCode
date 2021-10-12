package com.jason.leetCode;

/**
 * 你总共有n枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 * <p>
 * 给你一个数字n ，计算并返回可形成 完整阶梯行 的总行数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arranging-coins
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC441 {
    public static int arrangeCoins(int n) {
        int ans = 0;
        for (int i = 1; n > 0; i++) {
            n -= i;
            if (n >= 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins2(0));
    }

    // n = k*(k+1)/2 => k^2  + k - 2n  = 0
    // k1 = (-1 + 根号（1+4*2n） )/2  k2 = (-1 - 根号（1+4*2n） )/2 (负数)
    public static int arrangeCoins2(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }
}
