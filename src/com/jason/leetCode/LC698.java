package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/9/20 9:05:03
 * @description
 */
public class LC698 {
    int[] nums;
    int n, s;
    boolean[] cache;

    public boolean canPartitionKSubsets(int[] nums, int k) {//1 <= k <= nums.length <= 16  状态压缩  int
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        n = nums.length;
        s = sum / k;
        if (nums[n - 1] > s) {
            return false;
        }
        this.nums = nums;
        cache = new boolean[1 << n];
        Arrays.fill(cache, true);
        // 最大的超过了平均值，且无负数，凑不出来
        return dfs((1 << n) - 1, 0);
    }

    private boolean dfs(int state, int cur) {
        // 分完了
        if (state == 0) {
            return true;
        }
        if (!cache[state]) {
            return cache[state];
        }
        cache[state] = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] + cur > s) {
                break;
            }
            // 要当前这个数，
            if (((state >> i) & 1) != 0) {
                if (dfs(state ^ (1 << i), (cur + nums[i]) % s)) {
                    return true;
                }
            }
        }
        return false;
    }


}
