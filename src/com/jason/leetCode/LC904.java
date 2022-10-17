package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/17 9:16:05
 * @description
 */
public class LC904 {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int[] counts = new int[n];
        int l = 0, r = 0, kind = 0, ans = 0;
        while (r < n) {
            if (counts[fruits[r++]]++ == 0) {
                kind++;
            }
            if (kind > 2) {
                while (counts[fruits[l]] > 0) {
                    if (--counts[fruits[l++]] == 0) {
                        break;
                    }
                }
                kind--;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits = new int[]{1, 2, 3, 1, 2, 3};
        System.out.println(totalFruit(fruits));
    }
}
