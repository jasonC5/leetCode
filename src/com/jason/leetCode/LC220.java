package com.jason.leetCode;

import java.util.*;

public class LC220 {
    /**
     * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
     *
     * 如果存在则返回 true，不存在返回 false。
     *
     *
     *
     * 示例1：
     *
     * 输入：nums = [1,2,3,1], k = 3, t = 0
     * 输出：true
     * 示例 2：
     *
     * 输入：nums = [1,0,1,1], k = 1, t = 2
     * 输出：true
     * 示例 3：
     *
     * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出：false
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        int[] nums = {1,2};
        int k = 0, t = 1;
        final boolean b = Solution.containsNearbyAlmostDuplicate2(nums, k, t);
        System.out.println(b);
    }

    public static class Solution {
        public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            //abs(i - j) <= k  窗口大小为k
            //窗口内最小差值：t
            //1.窗口内排序
            //2.每个元素获取差值
            //3.只要差值<k，return true
            //4.循环结束，return false、
            if (nums == null || nums.length < 2) {
                return false;
            }
            final int length = nums.length;
            for (int i = 0; i<length; i++) {
                TreeSet<Integer> sortedSet = new TreeSet();//窗口内排序
                int end = Math.min(i+k , length-1);
                //窗口为i到end，能查询到两数相减<=t
                for (int j = i;j<=end;j++) {
                    if (sortedSet.isEmpty()) {
                        sortedSet.add(nums[j]);
                        continue;
                    }
                    Integer ceiling = sortedSet.ceiling(nums[j]);
                    Integer floor = sortedSet.floor(nums[j]);
                    if (ceiling != null && Math.abs((long)nums[j] - (long)ceiling) <= (long)t) {
                        return true;
                    } else if (floor != null && Math.abs((long)nums[j] - (long)floor) <= (long)t) {
                        return true;
                    }
                    sortedSet.add(nums[j]);
                }
                if (i+k>length-1) {
                    break;
                }
            }
            return false;
        }

        public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
            //abs(i - j) <= k  窗口大小为k
            //窗口内最小差值：t
            //1.窗口内排序
            //2.每个元素获取差值
            //3.只要差值<k，return true
            //4.循环结束，return false、
            if (nums == null || nums.length < 2 || k<=0) {
                return false;
            }
            final int length = nums.length;
            TreeSet<Integer> sortedSet = new TreeSet();//窗口内排序
            for (int i = 0; i<length; i++) {
                final int num = nums[i];
                if (sortedSet.isEmpty()) {
                    sortedSet.add(num);
                    continue;
                }
                Integer ceiling = sortedSet.ceiling(num);
                Integer floor = sortedSet.floor(num);
                if (ceiling != null && Math.abs((long)num - (long)ceiling) <= (long)t) {
                    return true;
                } else if (floor != null && Math.abs((long)num - (long)floor) <= (long)t) {
                    return true;
                }
                sortedSet.add(num);
                if (i >= k) {
                    sortedSet.remove(nums[i-k]);
                }
            }
            return false;
        }
    }
}

