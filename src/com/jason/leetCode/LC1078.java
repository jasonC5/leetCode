package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中 second 紧随 first 出现，third 紧随 second 出现。
 * <p>
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
 * 输出：["girl","student"]
 * 示例 2：
 * <p>
 * 输入：text = "we will we will rock you", first = "we", second = "will"
 * 输出：["we","rock"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/occurrences-after-bigram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1078 {
    public static String[] findOcurrences0(String text, String first, String second) {
        String[] s = text.split(" ");
        int mark = 0;
        List<String> ans = new ArrayList<>();
        for (String word : s) {
            if (mark == 0 && word.equals(first)) {
                mark = 1;
            } else if (mark == 1) {
                mark = word.equals(second) ? 2 : word.equals(first) ? 1 : 0;
            } else if (mark == 2) {
                ans.add(word);
                mark = word.equals(first) ? (word.equals(second) ? 2 : 1) : 0;
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
//        String text = "alice is a good girl she is a good student", first = "a", second = "good";
//        String text = text = "we will we will rock you", first = "we", second = "will";
//        String text = "jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa",
//                first = "kcyxdfnoa",
//                second = "jkypmsxd";
        String text = "we we we we will rock you",
                first = "we",
                second = "we";
        System.out.println(Arrays.toString(findOcurrences(text, first, second)));
    }

    public static String[] findOcurrences(String text, String first, String second) {
        String[] s = text.split(" ");
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < s.length; i++) {
            if (first.equals(s[i - 2]) && second.equals(s[i - 1])) {
                ans.add(s[i]);
            }
        }
        return ans.toArray(new String[0]);
    }
}
