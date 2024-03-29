package com.jason.leetCode;

/**
 * 给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * <p>
 * 返回将数组分隔变换后能够得到的元素最大和。
 * <p>
 * <p>
 * 注意，原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：
 * 因为 k=3 可以分隔成 [1,15,7] [9] [2,5,10]，结果为 [15,15,15,9,10,10,10]，和为 84，是该数组所有分隔变换后元素总和最大的。
 * 若是分隔成 [1] [15,7,9] [2,5,10]，结果就是 [1, 15, 15, 15, 10, 10, 10] 但这种分隔方式的元素总和（76）小于上一种。
 * 示例 2：
 * <p>
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * <p>
 * 输入：arr = [1], k = 1
 * 输出：1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-array-for-maximum-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1043 {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int length = arr.length;
        int[] dp = new int[length];
        dp[0] = arr[0];
        for (int i = 1; i < length; i++) {
            dp[i] = dp[i - 1] + arr[i];
            int max = arr[i];
            for (int len = 2; len <= k && i - len + 1 >= 0; len++) {
                max = Math.max(max, arr[i - len + 1]);
                dp[i] = Math.max(dp[i], max * len + (i - len >= 0 ? dp[i - len] : 0));
            }
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,4,1,5,7,3,6,1,9,9,3};
        int k = 4;
        System.out.println(maxSumAfterPartitioning(arr, k));
    }
}
