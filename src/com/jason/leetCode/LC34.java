package com.jason.leetCode;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回    [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC34 {
    public static void main(String[] args) {
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 8;
        int[] nums = {1};
        int target = 1;
        int[] ints = searchRange(nums, target);
        System.out.println(ints[0] + "," + ints[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = getTargetPre(nums, target) + 1;
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = getTargetPre(nums, target + 1);
        return new int[]{left, right};
    }

    //二分法查找小于指定数字的最大数字
    private static int getTargetPre(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
