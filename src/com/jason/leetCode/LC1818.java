package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author JasonC5
 */
public class LC1818 {

    public static void main(String[] args) {
        int[] arr1 = {1,10,4,4,2,7}, arr2 = {9,3,5,1,7,4};
        int i = minAbsoluteSumDiff(arr1, arr2);
        System.out.println(i);
    }

    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        //总和
        long oldSum = 0;
        //可减少的最大数字
        int maxSur = 0;
        //返回值需要取模
        int mod = 1000000007;
        int length = nums1.length;
        int[] clone = nums1.clone();
        Arrays.sort(clone);
        for (int i = 0; i < length; i++) {
            int val = Math.abs(nums1[i] - nums2[i]);
            oldSum = (oldSum + val) % mod;
            int x = findMinSur(clone, nums2[i]);//最小的差值
            maxSur = Math.max(maxSur, val - x);
        }
        return (int) (oldSum - maxSur + mod) % mod;
    }

    //找到arr数组中离自己最近的，并返回差值绝对值，arr有序
    private static int findMinSur(int[] arr, int find) {
        int left = 0, right = arr.length - 1;
        if (arr[right] < find) {
            return find - arr[right];
        } else if (arr[left] > find ) {
            return arr[left] - find;
        }
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (arr[mid] < find) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //left: 大于find的最小值的位置
        int minSur = arr[left] - find;
        if (left > 0) {
            minSur = Math.min(minSur, find - arr[left - 1]);
        }
        return minSur;
    }


}
