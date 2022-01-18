package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LC539 {

    // 有序表
    public static int findMinDifference(List<String> timePoints) {
        TreeSet<Integer> orderedTable = new TreeSet<Integer>((i1, i2) -> i1 - i2);
        // 为了少写两个if
        orderedTable.add(24 * 60 * 2 + 1);
        orderedTable.add(-24 * 60 * 2 - 1);
        int min = Integer.MAX_VALUE;
        for (String timePoint : timePoints) {
            String[] time = timePoint.split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            Integer floor = orderedTable.floor(minute);
            Integer ceiling = orderedTable.ceiling(minute);
            min = Math.min(min, ceiling - minute);
            min = Math.min(min, minute - floor);
            //
            floor = orderedTable.floor(minute + 24 * 60);
            ceiling = orderedTable.ceiling(minute + 24 * 60);
            min = Math.min(min, ceiling - minute + 24 * 60);
            min = Math.min(min, minute + 24 * 60 - floor);
            orderedTable.add(minute);
            orderedTable.add(minute + 24 * 60);
        }
        return min;
    }

    public static void main(String[] args) {
        List<String> timePoints = new ArrayList<>();
        timePoints.add("23:59");
        timePoints.add("00:00");
        System.out.println(findMinDifference2(timePoints));
    }

    // 排序
    public static int findMinDifference2(List<String> timePoints) {
        List<Integer> minuteList = new ArrayList<>();
        int ans = Integer.MAX_VALUE;
        for (String timePoint : timePoints) {
            String[] time = timePoint.split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            minuteList.add(minute);
        }
        minuteList.sort((i1, i2) -> i1 - i2);
        int pre = minuteList.get(0);
        for (int i = 1; i < minuteList.size(); i++) {
            int cur = minuteList.get(i);
            ans = Math.min(ans, cur - pre);
            pre = cur;
        }
        ans = Math.min(ans, minuteList.get(0) + 1440 - pre);
        return ans;
    }

}
