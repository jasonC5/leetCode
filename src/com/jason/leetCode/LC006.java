package com.jason.leetCode;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC006 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));
    }


    public static String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        char[] chars = s.toCharArray();
        int row = 0;
        boolean direction = true;//向下
        for (char aChar : chars) {
            builders[row].append(aChar);
            if ((direction && row == numRows - 1) || (!direction && row == 0)) {
                direction = !direction;
            }
            row = direction ? row + 1 : row - 1;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder builder : builders) {
            ans.append(builder);
        }
        return ans.toString();
    }
}
