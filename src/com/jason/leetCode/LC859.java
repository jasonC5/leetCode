package com.jason.leetCode;

/**
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 * <p>
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * <p>
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 * 示例 2：
 * <p>
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 * 示例 3：
 * <p>
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 * 示例 4：
 * <p>
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/buddy-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC859 {
    public static boolean buddyStrings(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length() || s.length() < 2) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] gChar = goal.toCharArray();
        int[] difIdx = new int[2];
        int[] charCount = new int[26];
        boolean hasSame = false;
        int difCount = 0;
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] != gChar[i]) {
                if (difCount == 2) {
                    return false;
                } else {
                    difIdx[difCount++] = i;
                }
            }
            if (++charCount[sChar[i] - 'a'] == 2) {
                hasSame = true;
            }
        }
        return (difCount == 2 && sChar[difIdx[0]] == gChar[difIdx[1]] && sChar[difIdx[1]] == gChar[difIdx[0]]) || (difCount == 0 && hasSame);
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("aa", "aa"));
    }
}
