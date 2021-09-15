package com.jason.leetCode;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC162 {
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        //三种情况：
        // 1.整个数组单调增，则nums[length-1]是峰值
        // 2.整个数组单调减，则nums[0]是峰值
        // 3.没有单调性，但设定上数组上相邻元素不能相等，则中间必有
        if (nums[0] > nums[1]) {
            return 0;
        } else if (nums[length - 1] > nums[length - 2]) {
            return length - 1;
        }
        // 中间
        int left = 0, right = length - 1;//此时左边增，右边降，中间必有峰值
        while (left <= right) {
            int mid = (left + right) >> 1;
            //4种情况
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                left = mid;
            } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {// 两遍肯定各有一个峰值，随便找一个即可
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }

}
