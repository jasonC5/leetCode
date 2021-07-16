package com.jason.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class Offer53 {

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(new Offer53().search2(arr, target));
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
//    public int search2(int[] nums, int target) {
//        int length = nums.length;
//        int l = 0, r = length - 1;
//        int leftIdx, rightIdx;
//        while (l < r) {
//            int mid = l + ((r - l) >> 1);
//            if (nums[mid] < target) {
//                l = mid + 1;
//            } else {
//                r = mid;
//            }
//        }
//        leftIdx = l;//左边界
//
//        l = 0;
//        r = length - 1;
//        while (l < r) {
//            int mid = l + ((r - l) >> 1);
//            if (nums[mid] <= target) {
//                l = mid + 1;
//            } else {
//                r = mid;
//            }
//        }
//        rightIdx = l;
//        return rightIdx - leftIdx + 1;
//    }
    public int search2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 哈希表法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.getOrDefault(target, 0);
    }
}
