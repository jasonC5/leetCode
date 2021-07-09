package com.jason.msjd;

/**
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class MS_17_10 {
    //超级水王问题

    public static void main(String[] args) {

    }

    public static int majorityElement(int[] nums) {
        //超级水王问题
        int maybeAns = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                maybeAns = nums[i];
                count++;
            } else if (maybeAns == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        if (count == 0) {
            return -1;
        }
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += maybeAns == nums[i] ? 1 : 0;
        }
        return count > (nums.length / 2) ? maybeAns : -1;
    }


}
