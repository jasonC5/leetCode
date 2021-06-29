package com.jason.leetCode;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "A"
 * 示例2:
 * <p>
 * 输入: 28
 * 输出: "AB"
 * 示例3:
 * <p>
 * 输入: 701
 * 输出: "ZY"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC168 {
    /**
     * 十进制转26进制
     *
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        if (columnNumber <= 0) {
            return null;
        }
        String ans = "";
        while (columnNumber > 0) {
//            int idx = (columnNumber - 1) % 26 + 1;
//            char x = (char) ('A' + idx - 1);
//            columnNumber = (columnNumber - idx) / 26;
//            ans = x + ans;

//            columnNumber--;
            ans = (char) ('A' + (--columnNumber % 26)) + ans;
            columnNumber /= 26;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(701));
    }
}
