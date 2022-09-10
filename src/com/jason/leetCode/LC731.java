package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC731 {

    class MyCalendarTwo {
        List<int[]> booked;//已预定
        List<int[]> covered;//已故覆盖

        public MyCalendarTwo() {
            booked = new ArrayList<>();
            covered = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            // 先校验，再插入
            for (int[] cov : covered) {
                // 已经重合过一次了，不能再预定
                if (cov[0] < end && cov[1] > start) {
                    return false;
                }
            }
            // 插入
            for (int[] book : booked) {
                // 有重合
                if (book[0] < end && book[1] > start) {
                    covered.add(new int[]{Math.max(book[0], start), Math.min(book[1], end)});
                }
            }
            booked.add(new int[]{start, end});
            return true;
        }
    }

}
