package com.jason.leetCode;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC88 {
    public static void main(String[] args) {
        int []nums1 = {4,5,6,0,0,0};
        int []nums2 = {1,2,3};
        int m = 3,  n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(nums1);
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int seek1=0;
//        int seek2=0;
//        int seek3=0;
        retry:
        for (int i = 0; i < n; i++) {
            //方法1：数组2一个一个往数组1里面插
            for (;;){
                if (seek1 == (m+i)){ //比数组1里面最大的还大，直接塞
                    nums1[seek1++] = nums2[i];
                    continue retry;
                }
                if (nums1[seek1] < nums2[i]) {//往后找
                    seek1++;
                } else {
                    //插入,先往后挪，空出位置
                    for (int j = 0; j < m+i-seek1; j++) {
                        nums1[m+i-j] = nums1[m+i-j-1];
                    }
                    nums1[seek1++] = nums2[i];
                    continue retry;
                }
            }
        }
    }
}
