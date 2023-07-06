package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/7/6 18:20:25
 * @description
 */
public class LC2178 {
    public static List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if ((finalSum & 1) == 1) {
            return ans;
        }
        long cur = 2L;
        while (finalSum >= (cur << 1) + 2) {
            ans.add(cur);
            finalSum -= cur;
            cur += 2;
        }
        if (finalSum > 0) {
            ans.add(finalSum);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maximumEvenSplit(28));
    }
}
