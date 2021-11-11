package com.jason.leetCode;

/**
 * 出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * <p>
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * <p>
 * 由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * 示例 2:
 * <p>
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-inverse-pairs-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC629 {
    static final int MOD = 1000000007;
    // 暴力递归：以最后一个数字来看

    /**
     * 1~n 排列 有k个逆序对的子数组，有多少
     *
     * @param n
     * @param k
     * @return
     */
    public static int kInversePairs0(int n, int k) {
        if (k == 0) {// 不再需要逆序对了，直接返回
            return 1;
        } else if (k < 0 || n == 1) {
            return 0;
        }
        // 从0~i 组成逆序对：j 的数组个数
        int ans = 0;
        for (int i = 1; i <= n; i++) {// n 插入到哪个的前面
            int produced = n - i;// 插入到这个位置时，产生的逆序对
            if (produced == k) {
                ans += 1;
            } else if (produced < k) {
                ans += kInversePairs0(n - 1, k - produced);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(kInversePairs(1000, 1000));
        System.out.println(kInversePairs1(1000, 1000));
    }

    // 暴力递归，改动态规划
    public static int kInversePairs1(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int j = 0; j <= k; j++) {
            dp[0][j] = 0;
            dp[1][j] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int x = 1; x <= i; x++) {// n 插入到哪个的前面
                    int produced = i - x;// 插入到这个位置时，产生的逆序对
                    if (produced == j) {
                        dp[i][j] = (dp[i][j] + 1) % MOD;
                    } else if (produced < j) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - produced]) % MOD;
                    }
                }
            }
        }
        return dp[n][k];
    }

    // 斜率优化
    public static int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        int[][] sum = new int[n + 1][k + 1];// 每行的sum[i][j] = dp[i][1~j]
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
//                for (int x = 1; x <= i; x++) {// n 插入到哪个的前面   1~i
//                    int produced = i - x;// 插入到这个位置时，产生的逆序对       0~i-1
//                    if (produced == j) {          //  i = j + 1
//                        dp[i][j] = (dp[i][j] + 1) % MOD;
//                    } else if (produced < j) {    // i-1 < j      0~i-1  或者 0~j-1         j-i+1
//                        dp[i][j] = (dp[i][j] + dp[i - 1][j - produced]) % MOD;// dp[i-1][1~j]  或者 dp[i-1][j- (i-1) ~ j]
//                    }
//                }
                dp[i][j] = (sum[i - 1][j] - (j > i - 1 ? sum[i - 1][j - i] : 0) + MOD) % MOD;
                if (i > j) {
                    dp[i][j] = (dp[i][j] + 1) % MOD;
                }
                sum[i][j] = (sum[i][j - 1] + dp[i][j]) % MOD;
            }
        }
        return dp[n][k];
    }

}
