package com.jason.offer;

/**
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * <p>
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 * <p>
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 * <p>
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/B1IidL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class OfferII069 {
    // 二分
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length;
        while (true) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid - 1] >= arr[mid] && arr[mid] >= arr[mid + 1]) {
                right = mid;
            } else if (arr[mid - 1] <= arr[mid] && arr[mid] <= arr[mid + 1]) {
                left = mid;
            } else {
                return mid;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0,1,0};
        System.out.println(peakIndexInMountainArray(arr));
    }
}
