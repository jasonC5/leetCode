package com.jason.leetCode;

/**
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * <p>
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PPALLP"
 * 输出：true
 * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
 * 示例 2：
 * <p>
 * 输入：s = "PPALLL"
 * 输出：false
 * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC551 {
    public static void main(String[] args) {
        System.out.println(checkRecord("AAAA"));
        System.out.println(checkRecord2("AAAA"));
    }

    public static boolean checkRecord(String s) {
        int absent = 0;
        int continuousLate = 0;
        for (char c : s.toCharArray()) {
            absent = c == 'A' ? absent + 1 : absent;
            continuousLate = c == 'L' ? continuousLate + 1 : 0;
            if (absent == 2 || continuousLate == 3) {
                return false;
            }
        }
        return true;
    }

    //状态压缩
    public static boolean checkRecord2(String s) {
        int mark = 0;
        for (char c : s.toCharArray()) {
            //后16位表示 Absent，缺勤
            mark = c == 'A' ? mark + 1 : mark;
            //前16位表示 连续迟到
            mark = c == 'L' ? mark + (1 << 16) : mark & 0x0000ffff;
            if (((mark & 0x0000ffff) == 2) || (mark >>> 16) == 3) {
                return false;
            }
        }
        return true;
    }
}
