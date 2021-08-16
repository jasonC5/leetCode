package com.jason.leetCode;

import java.util.List;

/**
 * 假设有从 1 到 N 的N个整数，如果从这N个数字中成功构造出一个数组，使得数组的第 i位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * <p>
 * 第i位的数字能被i整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * <p>
 * 示例1:
 * <p>
 * 输入: 2
 * 输出: 2
 * 解释:
 * <p>
 * 第 1 个优美的排列是 [1, 2]:
 * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * <p>
 * 第 2 个优美的排列是 [2, 1]:
 * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * 说明:
 * <p>
 * N 是一个正整数，并且不会超过15。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/beautiful-arrangement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC526 {

    public static void main(String[] args) {
//        System.out.println(countArrangement2(12));
//        System.out.println(countArrangement(12));
        System.out.println("start");
        for (int i = 1; i < 16; i++) {
            if (countArrangement3(i) != countArrangement2(i)) {
                System.out.println("error :" + i);
                return;
            }
        }
        System.out.println("finished");
    }

    //答案
    public static int countArrangement3(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        //0001~1111 全排列
        for (int mask = 1; mask < (1 << n); mask++) {
            //有几个1，
            int num = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                //这个位置上是优美排列
                if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }

    //暴力解，全排列，
    public static int countArrangement(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int idx) {
        if (idx == arr.length) {
            return beautyArray(arr, arr.length) ? 1 : 0;
        }
        int ans = 0;
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            ans += process(arr, idx + 1);
            swap(arr, idx, i);
        }
        return ans;
    }

    public static void swap(int[] chars, int idx1, int idx2) {
        int tmp = chars[idx1];
        chars[idx1] = chars[idx2];
        chars[idx2] = tmp;
    }

//    //是否优美排列
//    public static boolean beautyArray(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] % (i + 1) != 0 && (i + 1) % arr[i] != 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    //剪枝
    public static int countArrangement2(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return process2(arr, 0);
    }

    public static int process2(int[] arr, int idx) {
        if (idx == arr.length) {
            return beautyArray(arr, arr.length) ? 1 : 0;
        }
        //提前剪枝
        if (!beautyArray(arr, idx)) {
            return 0;
        }
        int ans = 0;
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            ans += process2(arr, idx + 1);
            swap(arr, idx, i);
        }
        return ans;
    }

    //0~idx上是否优美排列
    public static boolean beautyArray(int[] arr, int idx) {
        for (int i = 0; i < idx; i++) {
            if (arr[i] % (i + 1) != 0 && (i + 1) % arr[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
