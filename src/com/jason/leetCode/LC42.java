package com.jason.leetCode;

import java.util.Stack;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * @author JasonC5
 */
public class LC42 {
    // 单调栈
    public int trap0(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        //每个位置，后面第一次出现比它大的时候，计算中间的空间，然后填上
        for (int i = 0; i < height.length; i++) {
            //计算最后一次弹出来的即可
            while (!stack.isEmpty() && height[i] >= stack.peek()) {

            }
        }
        return ans;
    }

    public int trap(int[] height) {
        int length = height.length;
        int l = 1, r = length - 2;
        int leftBarrier = height[0];
        int rightBarrier = height[length - 1];
        int ans = 0;
        while (l <= r) {
            // 从低的一边开始算，高的留着
            if (leftBarrier < rightBarrier) {
                ans += Math.max(0, leftBarrier - height[l]);
                leftBarrier = Math.max(leftBarrier, height[l++]);
            } else {
                ans += Math.max(0, rightBarrier - height[r]);
                rightBarrier = Math.max(rightBarrier, height[r--]);
            }
        }
        return ans;
    }
}
