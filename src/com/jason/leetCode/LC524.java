package com.jason.leetCode;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC524 {

    public static String findLongestWord(String s, List<String> dictionary) {
        if (dictionary == null || dictionary.size() == 0 || s == null || s.length() == 0) {
            return "";
        }
        dictionary.sort((s1, s2) -> s1.length() == s2.length() ? (s1.compareTo(s2)) : s2.length() - s1.length());
        int strNum = dictionary.size();
        int[] matchSize = new int[strNum];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < strNum; j++) {
                String str = dictionary.get(j);
                if (matchSize[j] < str.length() && chars[i] == str.charAt(matchSize[j])) {
                    matchSize[j]++;
                }
            }
        }
        for (int i = 0; i < matchSize.length; i++) {
            String str = dictionary.get(i);
            if (matchSize[i] == str.length()) {
                return str;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "abpcplea";
//        String s = "a";
        List<String> dictionary = Arrays.asList("ale", "apple", "monkey", "plea");
//        List<String> dictionary = Arrays.asList("a");
        System.out.println(findLongestWord(s, dictionary));
        System.out.println(findLongestWord2(s, dictionary));
        System.out.println(findLongestWord3(s, dictionary));
    }

    public static String findLongestWord2(String s, List<String> dictionary) {
        if (dictionary == null || dictionary.size() == 0 || s == null || s.length() == 0) {
            return "";
        }
        dictionary.sort((s1, s2) -> s1.length() == s2.length() ? (s1.compareTo(s2)) : s2.length() - s1.length());
        for (String str : dictionary) {
            int i = 0, j = 0;//双指针
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                return str;
            }
        }
        return "";
    }

    // 不排序
    public static String findLongestWord3(String s, List<String> dictionary) {
        if (dictionary == null || dictionary.size() == 0 || s == null || s.length() == 0) {
            return "";
        }
        String ans = "";
        for (String str : dictionary) {
            int i = 0, j = 0;//双指针
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                if (ans.equals("")) {
                    ans = str;
                } else {
                    if (str.length() > ans.length() || (ans.length() == str.length() && str.compareTo(ans) < 0)) {
                        ans = str;
                    }
                }
            }
        }
        return ans;
    }

    // 动态规划
}
