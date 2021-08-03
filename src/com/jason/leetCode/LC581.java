package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC581 {

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(nums));
    }

    //    public static int findUnsortedSubarray(int[] nums) {
//        int left = 0, right = nums.length - 1;
//        //1.左边单调增的最大长度
//        //2.右边单调减的最大长度
//        //3.中间位置的最大值
//        for (int i = 1; i < right; i++) {
//            if (nums[i] > nums[i - 1]) {
//                left = i;
//            } else {
//                break;
//            }
//        }
//        if (left == right) {
//            return 0;
//        }
//        for (int i = nums.length - 1; i > left; i--) {
//            if (nums[i] > nums[i - 1]) {
//                right = i - 1;
//            } else {
//                break;
//            }
//        }
//        int max = nums[left + 1];
//        int min = nums[right - 1];
//        for (int i = left; i < right; i++) {
//            max = Math.max(max, nums[i]);
//            min = Math.min(min, nums[i]);
//        }
//        int old = left;
//        for (int i = old; i >= 0; i--) {
//            if (nums[i] > min) {
//                left = i - 1;
//            } else {
//                break;
//            }
//        }
//
//        old = right;
//        for (int i = old; i > left; i--) {
//            if (nums[i] < max) {
//                right = i;
//            } else {
//                break;
//            }
//        }
//        return right - left;
//    }
    public static int findUnsortedSubarray(int[] nums) {
        if (isSorted(nums)) {
            return 0;
        }
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        int left = 0;
        while (nums[left] == numsSorted[left]) {
            left++;
        }
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]) {
            right--;
        }
        return right - left + 1;
    }

    public static boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }


}
