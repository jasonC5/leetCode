package com.jason.leetCode;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC453 {
    /**
     * 最后的结果是要所有元素都相等，那么n-1个元素 + 1
     * 贪心的想法肯定是除了最大值都+1
     * 那么相对来说，可以转换成最大值-1
     * 所以转换成：所有元素相对最小值的差的总和
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
//        int min = Arrays.stream(nums).min().getAsInt();
        final int[] min = {Integer.MAX_VALUE};
        Arrays.stream(nums).forEach(num -> min[0] = Math.min(min[0], num));
        return Arrays.stream(nums).boxed().mapToInt(num -> num - min[0]).sum();
//        int min = Integer.MAX_VALUE;
//        for (int num : nums) {
//            min = Math.min(min, num);
//        }
//        int ans = 0;
//        for (int num : nums) {
//            ans += num - min;
//        }
//        return ans;
    }
}
