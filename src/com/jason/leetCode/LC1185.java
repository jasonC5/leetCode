package com.jason.leetCode;

public class LC1185 {

    public static final String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static final int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int[] monthPre;

    static {
        monthPre = new int[12];
        monthPre[0] = 0;
        for (int i = 1; i < 12; i++) {
            monthPre[i] = monthPre[i - 1] + month[i - 1];
        }
    }

    // 1970 年 1月 1日 周五
    public static String dayOfTheWeek(int day, int month, int year) {
        int days = (year - 1971) * 365 + (year - 1969) / 4;
        days += monthPre[month - 1];
        if (month > 2) {
            days += (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ? 1 : 0;
        }
        days += day;
        days += 3;
        return week[days % 7];
    }

    public static void main(String[] args) {
        System.out.println(dayOfTheWeek(31, 8, 2100));
    }


    public String dayOfTheWeek2(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        /* 输入年份之前的年份的天数贡献 */
        int days = 365 * (year - 1971) + (year - 1969) / 4;
        /* 输入年份中，输入月份之前的月份的天数贡献 */
        for (int i = 0; i < month - 1; ++i) {
            days += monthDays[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        /* 输入月份中的天数贡献 */
        days += day;
        return week[(days + 3) % 7];
    }
}
