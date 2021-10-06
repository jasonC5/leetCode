package com.jason.leetCode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 * <p>
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 * <p>
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC414 {
    public static int thirdMax(int[] nums) {
        //门槛堆 、 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((i1, i2) -> i1.compareTo(i2));
        //去重
        Set<Integer> visited = new HashSet<>();
        //记录最大值
        Integer max = Integer.MIN_VALUE;
        for (Integer num : nums) {
            if (!visited.contains(num)) {
                visited.add(num);
                max = Math.max(max, num);
                heap.offer(num);
                if (heap.size() > 3) {
                    heap.poll();
                }
            }
        }
        return heap.size() == 3 ? heap.poll() : max;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 1, 3, 6, 0};
        System.out.println(thirdMax2(nums));
    }
    //有序表
    public static int thirdMax2(int[] nums) {
        TreeSet<Integer> list = new TreeSet<>();
        for (int num : nums) {
            list.add(num);
            if (list.size() > 3) {
                list.remove(list.first());
            }
        }
        return list.size() == 3 ? list.first() : list.last();
    }


}
