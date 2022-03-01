package com.jason.leetCode;


/**
 * 象棋骑士有一个独特的移动方式，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个 L 的形状)。
 * <p>
 * 象棋骑士可能的移动方式如下图所示:
 * <p>
 * <p>
 * <p>
 * 我们有一个象棋骑士和一个电话垫，如下所示，骑士只能站在一个数字单元格上(即蓝色单元格)。
 * <p>
 * <p>
 * <p>
 * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
 * <p>
 * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是有效的骑士跳跃。
 * <p>
 * 因为答案可能很大，所以输出答案模 109 + 7.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-dialer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC935 {

    public static final int MOD = (int) (1e9 + 7);
    public static final int[][] MOVES = new int[][]{
            {4, 6},
            {6, 8},
            {7, 9},
            {4, 8},
            {3, 9, 0},
            {},
            {1, 7, 0},
            {2, 6},
            {1, 3},
            {2, 4}
    };

    public static int knightDialer0(int n) {
        int ans = 0;

        for (int i = 0; i < 10; i++) {
            ans += process(i, n);
        }
        return ans;
    }

    private static int process(int cur, int n) {
        if (n == 1) {
            return 1;
        }
        int ans = 0;
        for (int next : MOVES[cur]) {
            ans += process(next, n - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(knightDialer0(4));
        System.out.println(knightDialer(4));
    }

    public static int knightDialer(int n) {
        int[][] dp = new int[10][n + 1];
        for (int i = 0; i < 10; i++) {
            dp[i][1] = 1;
        }
        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < 10; i++) {
                for (int next : MOVES[i]) {
                    dp[i][j] = (dp[i][j] + dp[next][j - 1]) % MOD;
                }
            }
        }
        long ans = 0L;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[i][n]) % MOD;
        }
        return (int) ans;
    }

}
