package com.jason.jingsai.competition282;


/**
 * 给你两个字符串 s 和 t 。在一步操作中，你可以给 s 或者 t 追加 任一字符 。
 * <p>
 * 返回使 s 和 t 互为 字母异位词 所需的最少步骤数。
 * <p>
 * 字母异位词 指字母相同但是顺序不同（或者相同）的字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC2186 {

    // 题意理解错误 理解成了出现过的字母，真实题意是出现次数相同
    public static int minStepsF(String s, String t) {
        int sd = 0, td = 0;
        for (char c : s.toCharArray()) {
            sd |= 1 << (c - 'a');
        }
        for (char c : t.toCharArray()) {
            td |= 1 << (c - 'a');
        }
        int mask = sd | td;
        return count(sd, mask) + count(td, mask);
    }

    private static int count(int from, int to) {
        int count = 0;
        int mask = from ^ to;
        while (mask != 0) {
            mask -= mask & (-mask);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "leetcode", t = "coats";
        System.out.println(minSteps(s, t));
    }

    public static int minSteps(String s, String t) {
        int[] sd = new int[26], td = new int[26];
        for (char c : s.toCharArray()) {
            sd[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            td[c - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += Math.abs(sd[i] - td[i]);
        }
        return ans;
    }


}
