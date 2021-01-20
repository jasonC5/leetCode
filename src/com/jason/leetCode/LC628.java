package com.jason.leetCode;

/**
 * 628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC628 {

    public int maximumProduct(int[] nums) {
        //如果都大于0->三个最大数字相乘
        //如果都小于0->负的越小越好，三个最大数字相乘
        //如果有正有负，两个最小的负数（绝对值最大）加一个最大的正数，或者三个最大的正数相乘，取最大的那个
        //综合来讲 Max(M1*M2*M3, M1*Min1*Min2);
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE,max3 = Integer.MIN_VALUE,min1=Integer.MAX_VALUE,min2=Integer.MAX_VALUE;
        for(int v : nums){
            if(v > max1){
                max3 = max2;
                max2 = max1;
                max1 = v;
            } else if(v > max2){
                max3 = max2;
                max2 = v;
            } else if(v > max3){
                max3 = v;
            }

            if(v < min1){
                min2 = min1;
                min1 = v;
            } else if(v < min2){
                min2 = v;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
