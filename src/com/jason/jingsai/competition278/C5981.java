package com.jason.jingsai.competition278;

import java.util.ArrayList;
import java.util.List;

public class C5981 {
    public static List<Integer> maxScoreIndices(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int len = nums.length;
        int zeroCount = 0;
        int[] preZoreCount = new int[len + 1];
        for (int i = 0; i < len; i++) {
            zeroCount += nums[i] == 0 ? 1 : 0;
            preZoreCount[i + 1] = zeroCount;
        }
        int oneCount = len - zeroCount;
        int maxFraction = 0;//分数
        for (int i = 0; i <= len; i++) {
            int curFraction = preZoreCount[i] + oneCount - i + preZoreCount[i];
            if (curFraction > maxFraction) {
                maxFraction = curFraction;
                ans.clear();
                ans.add(i);
            } else if (curFraction == maxFraction) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {0, 0, 1, 0};
        System.out.println(maxScoreIndices(num));
    }
}
