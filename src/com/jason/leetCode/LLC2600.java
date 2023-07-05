package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/5 10:19:49
 * @description
 */
public class LLC2600 {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        return k <= numOnes ? k : (k <= numOnes + numZeros ? numOnes : (numOnes << 1) + numZeros - k);
    }
}
