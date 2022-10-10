package com.jason.leetCode;

import java.util.Arrays;

/**
 * 我们有两个长度相等且不为空的整型数组 nums1 和 nums2 。在一次操作中，我们可以交换 nums1[i] 和 nums2[i]的元素。
 * <p>
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回 使 nums1 和 nums2 严格递增 所需操作的最小次数 。
 * <p>
 * 数组 arr 严格递增 且  arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1] 。
 * <p>
 * 注意：
 * <p>
 * 用例保证可以实现操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/10/10 14:20:59
 * @description
 */
public class LC801 {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        return process(nums1, nums2, n, 0, 0, dp);
    }

    /**
     * @param nums1
     * @param nums2
     * @param n
     * @param i     当前位置
     * @param pre   前一位置换还是不换
     * @param dp    缓存
     * @return
     */
    private int process(int[] nums1, int[] nums2, int n, int i, int pre, int[][] dp) {
        if (i == n) {
            return 0;
        }
        if (dp[pre][i] != -1) {
            return dp[pre][i];
        }
        // 换
        int p1 = Integer.MAX_VALUE;
        // 不换
        int p2 = Integer.MAX_VALUE;
        // 前一位没换
        if (pre == 0) {
            // 换
            if (i == 0 || (nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1])) {
                int next = process(nums1, nums2, n, i + 1, 1, dp);
                if (next != Integer.MAX_VALUE) {
                    p1 = 1 + next;
                }
            }
            // 不换
            if (i == 0 || (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1])) {
                p2 = process(nums1, nums2, n, i + 1, 0, dp);
            }
        } else {
            // 前一位换了
            // 换
            if (i == 0 || (nums2[i] > nums2[i - 1] && nums1[i] > nums1[i - 1])) {
                int next = process(nums1, nums2, n, i + 1, 1, dp);
                if (next != Integer.MAX_VALUE) {
                    p1 = 1 + next;
                }
            }
            // 不换
            if (i == 0 || (nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1])) {
                p2 = process(nums1, nums2, n, i + 1, 0, dp);
            }
        }
        dp[pre][i] = Math.min(p1, p2);
        return dp[pre][i];
    }


    /**
     * DP
     * @param nums1
     * @param nums2
     * @return
     */
    public int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        dp[0][n] = 0;
        dp[1][n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int pre = 0; pre < 2; pre++) {
                int p1 = Integer.MAX_VALUE;
                int p2 = Integer.MAX_VALUE;
                if (pre == 0) {
                    if (i == 0 || (nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1])) {
                        int next = dp[1][i + 1];
                        if (next != Integer.MAX_VALUE) {
                            p1 = 1 + next;
                        }
                    }
                    if (i == 0 || (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1])) {
                        p2 = dp[0][i+1];
                    }
                } else {
                    if (i == 0 || (nums2[i] > nums2[i - 1] && nums1[i] > nums1[i - 1])) {
                        int next =  dp[1][i + 1];
                        if (next != Integer.MAX_VALUE) {
                            p1 = 1 + next;
                        }
                    }
                    if (i == 0 || (nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1])) {
                        p2 = dp[0][i+1];
                    }
                }
                dp[pre][i] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


}
