package com.jason.jingsai.competition283;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 6017. 向数组中追加 K 个整数 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Medium
 * 给你一个整数数组 nums 和一个整数 k 。请你向 nums 中追加 k 个 未 出现在 nums 中的、互不相同 的 正 整数，并使结果数组的元素和 最小 。
 *
 * 返回追加到 nums 中的 k 个整数之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,25,10,25], k = 2
 * 输出：5
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
 * nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。
 * 示例 2：
 *
 * 输入：nums = [5,6], k = 6
 * 输出：25
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
 * nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
 * @author JasonC5
 */
public class Code2 {

    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        int length = nums.length;
        long ans = (long) (1 + length + k) * (length + k) / 2;
        Set<Integer> visited = new HashSet<>();
        int seek = 0;//从length + k往前，有几个不用加
        for (int num : nums) {
            if (num < length + k - seek && !visited.contains(num)) {
                ans -= num;
                visited.add(num);
            } else {
                ans -= length + k - seek++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {96, 44, 99, 25, 61, 84, 88, 18, 19, 33, 60, 86, 52, 19, 32, 47, 35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84};
        int k = 35;
//        int[] nums = {1, 4, 7, 10, 25};
//        int k = 2;
//        Arrays.sort(nums);
        System.out.println(minimalKSum(nums, k));
    }

    public long minimalKSum0(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0;
        int start = 1;
        for (int num : nums) {
            if (num > start) {
                int sur = num - start;
                if (sur >= k) {
                    ans += (long) k * (start + start + k - 1) >> 1;
                    return ans;
                } else {
                    ans += (long) sur * (start + start + sur - 1) >> 1;
                    k -= sur;
                }
            }
            start = num + 1;
        }
        ans += (long) k * (start + start + k - 1) >> 1;
        return ans;
    }
}
