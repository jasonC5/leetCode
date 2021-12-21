package com.jason.leetCode;

public class LC1154 {
    public static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int dayOfYear(String date) {
        String[] d = date.split("-");
        int year = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);
        int idx = 0;
        for (int i = 0; i < month; i++) {
            idx += days[i];
        }
        idx += (month > 2 && year % 4 == 0) ? 1 : 0;
        return idx + day;
    }

    public static void main(String[] args) {
        System.out.println(dayOfYear("2004-03-01"));
    }
}
