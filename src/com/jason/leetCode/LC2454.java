package com.jason.leetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author chenjieaj
 * @date 2023/12/12 10:16:46
 * @description
 */
public class LC2454 {
    public static int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        // 两个单调栈
        Stack<int[]> stack1 = new Stack<>();
        Stack<int[]> stack2 = new Stack<>();
        Stack<int[]> tmp = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 弹出第二次比它大的
            while (!stack2.isEmpty() && nums[i] > stack2.peek()[0]) {
                ans[stack2.pop()[1]] = nums[i];
            }
            // 弹出第一次比它大的
            while (stack1.size() > 0 && nums[i] > stack1.peek()[0]) {
                tmp.push(stack1.pop());
            }
            //
            while (!tmp.isEmpty()) {
                stack2.push(tmp.pop());
            }
            stack1.push(new int[]{nums[i], i});
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {11, 13, 15, 12, 0, 15, 12, 11, 9};
        System.out.println(Arrays.toString(secondGreaterElement(arr)));
    }
}
