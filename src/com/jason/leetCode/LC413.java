package com.jason.leetCode;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * <p>
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * <p>
 * 子数组 是数组中的一个连续序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC413 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(new LC413().numberOfArithmeticSlices(arr));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int ans = 0;
        //以i开头，有几个子等差数组
        for (int i = 0; i < nums.length; i++) {
            for (int length = 3; i + length <= nums.length; length++) {
                if (check(nums, i, i + length - 1)) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    //检查从left到right，是否是等差数组
    private boolean check(int[] arr, int left, int right) {
        int sur = arr[left + 1] - arr[left];
        for (int i = left + 2; i <= right; i++) {
            if (arr[i] - arr[i - 1] != sur) {
                return false;
            }
        }
        return true;
    }

}
