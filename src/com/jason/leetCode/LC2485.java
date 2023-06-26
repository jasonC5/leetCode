package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/6/26 9:26:42
 * @description
 */
public class LC2485 {
    // (1+n)*n/2
    public int pivotInteger(int n) {
        int sum = ((1 + n) * n) >> 1;
        int x = (int) Math.sqrt(sum);
        return x * x == sum ? x : -1;
    }
}
