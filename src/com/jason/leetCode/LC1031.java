package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/4/26 9:07:30
 * @description
 */
public class LC1031 {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(process(nums, firstLen, secondLen), process(nums, secondLen, firstLen));
    }

    private int process(int[] nums, int fstLen, int secLen) {
        int ans = 0, fstSum = 0, secSum = 0, maxL = 0;
        for (int i = 0; i < fstLen; i++) {
            fstSum += nums[i];
        }
        for (int i = fstLen; i < fstLen + secLen; i++) {
            secSum += nums[i];
        }
        maxL = fstSum;
        ans = fstSum + secSum;
        for (int i = fstLen; i < nums.length - secLen; i++) {
            fstSum = fstSum - nums[i - fstLen] + nums[i];
            maxL = Math.max(maxL, fstSum);
            secSum = secSum - nums[i] + nums[i + secLen];
            ans = Math.max(ans, maxL + secSum);
        }
        return ans;
    }
}
