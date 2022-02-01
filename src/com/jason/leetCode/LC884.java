package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * <p>
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * <p>
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 * 示例 2：
 * <p>
 * 输入：s1 = "apple apple", s2 = "banana"
 * 输出：["banana"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC884 {
    public static String[] uncommonFromSentences(String s1, String s2) {
        Set<String> onceSet = new HashSet<>();
        Set<String> multipleSet = new HashSet<>();
        for (String str : s1.split(" ")) {
            if (onceSet.contains(str)) {
                onceSet.remove(str);
                multipleSet.add(str);
            } else if (!multipleSet.contains(str)) {
                onceSet.add(str);
            }
        }
        for (String str : s2.split(" ")) {
            if (onceSet.contains(str)) {
                onceSet.remove(str);
                multipleSet.add(str);
            } else if (!multipleSet.contains(str)) {
                onceSet.add(str);
            }
        }
        return onceSet.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String s1 = "this apple is sweet", s2 = "this apple is sour";
        System.out.println(Arrays.toString(uncommonFromSentences(s1, s2)));
    }

}
