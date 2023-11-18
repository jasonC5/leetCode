package com.jason.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/11/18 9:07:41
 * @description
 */
public class LC2342 {
    public int maximumSum(int[] nums) {
        int ans = -1;
        // 之前位数和为X的时候的最大值
        Map<Integer, Integer> beforeInfo = new HashMap<>();
        for (int num : nums) {
            int byteSum = getSum(num);
            if (beforeInfo.containsKey(byteSum)) {
                Integer beforeMax = beforeInfo.get(byteSum);
                ans = Math.max(ans, num + beforeMax);
                beforeInfo.put(byteSum, Math.max(beforeMax, num));
            } else {
                beforeInfo.put(byteSum, num);
            }
        }
        return ans;
    }

    private int getSum(int num) {
        int sum = 0;
        while (num > 0){
            sum += num %10;
            num /= 10;
        }
        return sum;
    }
}
