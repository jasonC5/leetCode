package com.jason.leetCode;

/**
 * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
 * <p>
 * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
 * <p>
 * 替换time 中隐藏的数字，返回你可以得到的最晚有效时间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：time = "2?:?0"
 * 输出："23:50"
 * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
 * 示例 2：
 * <p>
 * 输入：time = "0?:3?"
 * 输出："09:39"
 * 示例 3：
 * <p>
 * 输入：time = "1?:22"
 * 输出："19:22"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1736 {
    public static void main(String[] args) {
//        System.out.println(maximumTime("??:??"));
//        System.out.println(maximumTime("1?:??"));
//        System.out.println(maximumTime("2?:??"));
//        System.out.println(maximumTime("21:2?"));
//        System.out.println(maximumTime("21:?3"));
//        System.out.println(maximumTime("0?:3?"));
        System.out.println('?' > '3');
    }


    // 00:00 到 23:59
    public static String maximumTime(String time) {
        //一个到几个数字为？？，先搞定前两位，00~23，再搞定后面两位，00~59
//        String[] split = time.split(":");
//        String hour = split[0];
//        int hourVal = 0;
//        String minute = split[1];
//        int minuteVal = 0;
//        if (hour.equals("??")) {
//            hourVal = 23;
//        } else {
//            if (hour.indexOf("?") == 0) {
//                hour = hour.replace("?", "2");
//                hourVal = Integer.valueOf(hour);
//                while (hourVal > 23) {
//                    hourVal -= 10;
//                }
//            } else if (hour.indexOf("?") == 1) {
//                hour = hour.replace("?", "9");
//                hourVal = Integer.valueOf(hour);
//                while (hourVal > 23) {
//                    hourVal -= 1;
//                }
//            } else {
//                hourVal = Integer.valueOf(hour);
//            }
//        }
//        if (minute.equals("??")) {
//            minuteVal = 59;
//        } else {
//            if (minute.indexOf("?") == 0) {
//                minute = minute.replace("?", "5");
//                minuteVal = Integer.valueOf(minute);
//            } else if (minute.indexOf("?") == 1) {
//                minute = minute.replace("?", "9");
//                minuteVal = Integer.valueOf(minute);
//            } else {
//                minuteVal = Integer.valueOf(minute);
//            }
//        }
//        return (hourVal < 10 ? "0" + hourVal : hourVal) + ":" + (minuteVal < 10 ? "0" + minuteVal : minuteVal);
        char[] chars = time.toCharArray();
        if (chars[0] == '?') {
            chars[0] = (chars[1] > '3' && chars[1] != '?') ? '1' : '2';
        }
        if (chars[1] == '?') {
            chars[1] = chars[0] == '2' ? '3' : '9';
        }
        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }
        return new String(chars);
    }
}
