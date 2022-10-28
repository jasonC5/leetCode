package com.jason.leetCode;

/**
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * <p>
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 *
 * @author chenjieaj
 * @date 2022/10/28 9:22:29
 * @description
 */
public class LC907 {
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        // 单调栈，左边小于等于自己的最近位置，右边小于等于自己的最近位置，
        int[] left = new int[n];
        int[] right = new int[n];
        int[] stack = new int[n];
        int point = 0;
        for (int i = 0; i < n; i++) {
            while (point != 0 && arr[i] <= arr[stack[point - 1]]) {
                point--;
            }
            // 左边可扩的长度
            left[i] = i - (point == 0 ? -1 : stack[point - 1]);
            stack[point++] = i;
        }
        point = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (point != 0 && arr[i] < arr[stack[point - 1]]) {
                point--;
            }
            // 右边可扩的长度
            right[i] = (point == 0 ? n : stack[point - 1]) - i;
            stack[point++] = i;
        }
        long ans = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
    }
}
