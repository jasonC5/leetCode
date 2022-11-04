package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/4 9:04:26
 * @description
 */
public class LC754 {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }
//        return k;
        return target % 2 == 0 ? k : (k + 1 + (k & 1));
    }
}
