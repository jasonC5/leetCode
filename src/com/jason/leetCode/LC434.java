package com.jason.leetCode;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC434 {
    public static int countSegments(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int ans = 0;
        boolean flag = false;
        for (char c : chars) {
            if (c == ' ' && flag) {
                ans++;
                flag = false;
            }
            if (c != ' ') {
                flag = true;
            }
        }
        if (chars[chars.length - 1] != ' ') {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "Hello, my name is John";
        System.out.println(countSegments(str));
    }

}
