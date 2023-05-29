package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/5/29 9:40:48
 * @description
 */
public class LC2455 {
    public int averageValue(int[] nums) {
        long sum = 0L;
        int cnt = 0;
        for (int num : nums) {
            if ((num & 1) == 0 && num % 3 == 0) {
                sum += num;
                cnt++;
            }
        }
        return cnt == 0 ? 0 : (int) (sum / cnt);
    }
}
