package com.jason.jingsai.competition275;

import java.util.Arrays;

/**
 * @author JasonC5
 */
public class C5979 {
    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        int len = plantTime.length;
        int[][] info = new int[len][2];
        for (int i = 0; i < len; i++) {
            info[i][0] = plantTime[i];
            info[i][1] = growTime[i];
        }
        Arrays.sort(info, (i1, i2) -> i2[1] - i1[1]);
        int cur = 0;
        int allGrowDay = 0;
        for (int i = 0; i < len; i++) {
            cur += info[i][0];// 播种完成
            allGrowDay = Math.max(allGrowDay, cur + info[i][1]);//开花
        }
        return allGrowDay;
    }

    public static void main(String[] args) {
        int[] plantTime = {1, 2, 3, 2}, growTime = {2, 1, 2, 1};
        System.out.println(earliestFullBloom(plantTime, growTime));
    }
}
