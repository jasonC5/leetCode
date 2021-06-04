package com.jason.leetCode;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author JasonC5
 */
public class LC004 {

    public static void main(String[] args) {
        int[] a = {1,2};
        int[] b = {3,4};
        System.out.println(findMedianSortedArrays(a,b));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //中位数：奇数arr[n/2+1] 偶数：(arr[n/2]+arr[n/2+1])/2
        //双指针法
        int length1 = nums1.length;
        int length2 = nums2.length;
        int sum = length1 + length2;
        if ((sum & 1) ==0) {//偶数
            return (double) (findNumber(nums1, nums2, (sum/2)) + findNumber(nums1, nums2, (sum/2)+1)) / 2;
        } else {
            return findNumber(nums1, nums2, (sum/2)+1);
        }

    }

    public static int findNumber(int[] nums1, int[] nums2, int index){
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 + length2 < index) {
            return 0;
        }
        int point1 = 0;
        int point2 = 0;
        while (true){
            if (point2==length2) {
                if (++point1 + point2 == index){
                    return nums1[point1-1];
                }
            } else if (point1==length1) {
                if (point1 + ++point2 == index){
                    return nums2[point2-1];
                }
            } else {
                if (nums1[point1] < nums2[point2]) {
                    if (++point1 + point2 == index){
                        return nums1[point1-1];
                    }
                } else {
                    if (point1 + ++point2 == index){
                        return nums2[point2-1];
                    }
                }
            }
        }

    }
}
