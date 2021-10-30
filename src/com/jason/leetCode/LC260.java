package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 * <p>
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 * <p>
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC260 {
    public static int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        //xor = a ^ b
        // 由于a!=b，所以，二进制上，必定至少有一位是不相等的
        // 只需要取到任意一个不相等的位数，把nums分成两个部分，此时这两个部分内部就只有1一个出现一次的了，通过异或找出来
        int mask = xor & (-xor);//找到最右边的1，这个位置上 a b 肯定一个是0，一个是1
        int a = 0;//其中一个
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            }
        }
        return new int[]{a, xor ^ a};
    }

    public static void main(String[] args) {
        int[] nums = {2,1,2,3,4,1};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}
