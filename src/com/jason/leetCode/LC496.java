package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
 * <p>
 * 请你找出 nums1中每个元素在nums2中的下一个比其大的值。
 * <p>
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * <p>
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC496 {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //单调栈
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (stack.size() > 0 && num > stack.peek()) {
                Integer pop = stack.pop();
                map.put(pop, num);
            }
            stack.add(num);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
        int[] nums1 = {2, 4}, nums2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(nextGreaterElement2(nums1, nums2)));
    }

    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        //单调栈
        int[] stack = new int[nums2.length];
        int index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (index > 0 && num > stack[index - 1]) {
                int pop = stack[--index];
                map.put(pop, num);
            }
            stack[index++] = num;
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
