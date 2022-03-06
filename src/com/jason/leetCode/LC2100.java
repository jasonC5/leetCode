package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC2100 {
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        int[] preDays = new int[length];//当前天数前连续几天警卫人数非递增
        int[] postDays = new int[length];//当前天数后连续几天警卫人数非递减
        for (int i = 1; i < length; i++) {
            preDays[i] = security[i] <= security[i - 1] ? preDays[i - 1] + 1 : 0;
            postDays[length - 1 - i] = security[length - 1 - i] <= security[length - i] ? postDays[length - i] + 1 : 0;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (preDays[i] >= time && postDays[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int time = 2;
        int[] security = {1,2,3,4,5,6};
        System.out.println(goodDaysToRobBank(security, time));
    }
}
