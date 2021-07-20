package com.jason.leetCode;

import java.util.Arrays;

/**
 * 素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1838 {

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 100;
        System.out.println(maxFrequency(nums, k));
    }

    public static int maxFrequency(int[] nums, int k) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        } else if (nums.length == 1) {
//            return 1;
//        }
        Arrays.sort(nums);
        int ans = 1, left = 0, cost = 0;
        //右指针往右扩，消耗执行次数，左指针往右移，减少执行次数
        for (int right = 1; right < nums.length; right++) {
            //每次扩，需要消耗的次数
            cost += (nums[right] - nums[right - 1]) * (right - left);
            //如果超过了，需要缩
            while (cost > k) {
                //每次缩一个，最左边放出来的次数
                cost -= nums[right] - nums[left++];
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
