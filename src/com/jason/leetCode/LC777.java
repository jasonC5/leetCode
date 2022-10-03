package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/2 9:44:34
 * @description
 */
public class LC777 {
    public boolean canTransform(String start, String end) {
//        return start.replace("X", "").equals(end.replace("X", ""));
        int n = start.length();
        int x = 0, y = 0;// 双指针
        while (true) {
            while (x < n && start.charAt(x) == 'X') {
                x++;
            }
            while (y < n && end.charAt(y) == 'X') {
                y++;
            }
            // 校验完成
            if (x == n && y == n) {
                return true;
            }
            // 无法到达
            if (x == n || y == n || start.charAt(x) != end.charAt(y)) {
                return false;
            }
            // start 当前位置只能往左边移动，但是已经追不上end指针
            if (start.charAt(x) == 'L' && x < y) {
                return false;
            }
            // start 指针上的R只能往右边移动，不可能能到达end指针的位置
            if (start.charAt(x) == 'R' && x > y) {
                return false;
            }
            x++;
            y++;
        }
    }
}
