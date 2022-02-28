package com.jason.jingsai.competition282;

/**
 * 给你一个字符串数组 words 和一个字符串 pref 。
 * <p>
 * 返回 words 中以 pref 作为 前缀 的字符串的数目。
 * <p>
 * 字符串 s 的 前缀 就是  s 的任一前导连续字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-words-with-a-given-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC2185 {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            ans += word.startsWith(pref) ? 1 : 0;
        }
        return ans;
    }
}
