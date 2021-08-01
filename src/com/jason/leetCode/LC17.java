package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC17 {

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }

    public static final char[][] MAP = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    //深度优先遍历
    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }
        char[] chars = digits.toCharArray();
        char[] tmp = new char[digits.length()];
        process(chars, 0, tmp, ans);
        return ans;
    }

    private static void process(char[] chars, int index, char[] tmp, List<String> ans) {
        if (index == chars.length) {
            ans.add(new String(tmp));
            return;
        }
        char[] chars1 = MAP[chars[index] - '2'];
        for (char c : chars1) {
            tmp[index] = c;
            process(chars, index + 1, tmp, ans);
        }
    }
}
