package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/23 10:09:45
 * @description
 */
public class LC1742 {
    public int countBalls(int lowLimit, int highLimit) {
        // 最多5个9
        int[] container = new int[46];
        for (int i = lowLimit; i <= highLimit; i++) {
            int bitSum = bitSum(i);
            container[bitSum]++;
        }
        int ans = 0;
        for (int j : container) {
            ans = Math.max(ans, j);
        }
        return ans;
    }

    private int bitSum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += i % 10;
            i /= 10;
        }
        return ans;
    }
}
