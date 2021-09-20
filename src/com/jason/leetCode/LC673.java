package com.jason.leetCode;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC673 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        System.out.println(findNumberOfLIS(nums));
    }

    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLen = 1;
        int length = nums.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 1;//最长子序列
        dp[0][1] = 1;//长度为最长子序列的数量count
        for (int i = 1; i < length; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        //更新最长子序列长度
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                        maxLen = Math.max(maxLen, dp[i][0]);
                    } else if (dp[j][0] + 1 == dp[i][0]) {
                        // 相等，+数量
                        dp[i][1] += dp[j][1];
                    } else {
                        // 比当前最长子序列短，不处理
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (dp[i][0] == maxLen) {
                ans += dp[i][1];
            }
        }
        return ans;
    }
}
