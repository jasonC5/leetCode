package com.jason.leetCode;

import java.util.Arrays;

/**
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 * <p>
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * <p>
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 * 示例 2：
 * <p>
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * 示例 3：
 * <p>
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC881 {
    public int numRescueBoats(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int length = arr.length;
        //一艘船运不了一个人，完不成的任务
        if (arr[length - 1] > limit) {
            return -1;
        }
        //右指针limit/2的右边位置、左指针指向limit/2左边的位置
        //左边指针往左移，能能满足右指针的时候打钩，不能则打×
        int lessRight = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i] <= (limit >> 1)) {
                lessRight = i;
                break;
            }
        }
        //就没一个人在limit/2以下的，每个人一艘船
        if (lessRight == -1) {
            return length;
        }
        int left = lessRight;
        int right = lessRight + 1;
        //用了
        int noUsed = 0;
        while (left >= 0) {
            int solved = 0;
            while (right < length && arr[left] + arr[right] <= limit) {
                solved++;
                right++;
            }
            if (solved == 0) {
                left--;
                //左边打叉
                noUsed++;
            } else {
                left = Math.max(-1, left - solved);
            }
        }
        //左边有多少配对了【右边就有多少配对了】
        int picked = lessRight + 1 - noUsed;
        //左边还剩多少,两个人一艘船
        int leftRestCost = (noUsed + 1) >> 1;
        //右边还剩多少
        int rightRestRest = (length - lessRight - 1) - picked;
        return picked + leftRestCost + rightRestRest;
    }
}
