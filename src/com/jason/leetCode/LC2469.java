package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/21 14:01:24
 * @description
 */
public class LC2469 {
    public double[] convertTemperature(double celsius) {
        double[] ans = new double[2];
        ans[0] = celsius + 273.15;
        ans[1] = celsius * 1.80 + 32.00;
        return ans;
    }
}
