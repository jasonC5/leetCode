package com.jason.leetCode;

import java.util.*;

/**
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * <p>
 * 美式键盘 中：
 * <p>
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * <p>
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 * <p>
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keyboard-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC500 {
    public static final Map<Character, Integer> map = new HashMap();

    static {
        String[] strs = new String[]{"QWERTYUIOPqwertyuiop", "ASDFGHJKLasdfghjkl", "ZXCVBNMzxcvbnm"};
        for (int i = 1; i <= 3; i++) {
            String s = strs[i - 1];
            char[] chars = s.toCharArray();
            for (char c : chars) {
                map.put(c, i);
            }
        }
    }

    public static String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        int count = 0;
        find:
        for (String word : words) {
            char[] chars = word.toCharArray();
            Integer flag = map.get(chars[0]);
            for (char c : chars) {
                if (!map.get(c).equals(flag)) {
                    continue find;
                }
            }
            ans.add(word);
        }
        return ans.toArray(new String[count]);
    }

    public static void main(String[] args) {
        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
        System.out.println(Arrays.toString(findWords(words)));
    }
}
