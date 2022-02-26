package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * <p>
 * 示例 1:
 * <p>
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 * <p>
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC719 {
    public static int smallestDistancePair(int[] nums, int k) {
        int ans = 0;
        int length = nums.length;
        Arrays.sort(nums);
        int l = 0;//距离下限
        int r = nums[length - 1] - nums[0];//距离上限
        while (l <= r) {
            int dist = l + ((r - l) >> 1);
            int count = countFunc(nums, dist);//小于等于这个距离，有多少个数对
            if (count < k) {
                //要找第k小的距离， 当距离为dist时， <=dist的数对小于k，所以要拉大距离
                l = dist + 1;
            } else {
                // count >=k ，
                // 就算等于也不能确定现在的dist就是答案，因为这个值是二分来的，并不一定是真实存在的，所以要找到 最小的，>=k的dist
                ans = dist;// 先记录下
                r = dist - 1;
            }
        }
        return ans;
    }

    //小于等于距离dist，有多少个数对
    private static int countFunc(int[] nums, int dist) {
        int count = 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            for (; left <= right; left++) {
                if (nums[right] - nums[left] <= dist) {
                    break;
                }
            }
            count += right - left;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,1};
        int k = 1;
        System.out.println(smallestDistancePair(nums, k));
    }
}
