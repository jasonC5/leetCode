package com.jason.offer;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 *
 * @author JasonC5
 */
public class Offer42 {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSonSum(arr));
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int sumMax = 0;
        for (int num : nums) {
            sumMax = sumMax > 0 ? (sumMax + num) : num;
            max = Math.max(sumMax, max);
        }
        return max;
    }


    public static int maxSonSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, arr.length - 1);
    }

    private static int process(int[] arr, int idx) {
        if (idx == 0) {
            return arr[0] < 0 ? 0 : arr[0];
        }
        int p1 = arr[idx];
        int p2 = arr[idx] + process(arr, idx - 1);
        return (Math.max(p1, p2));
    }


}
