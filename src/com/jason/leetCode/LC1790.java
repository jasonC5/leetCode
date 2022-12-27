package com.jason.leetCode;

/**
 * 给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 * <p>
 * 如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1790 {
    public static boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int idx1 = -1, idx2 = -1, cnt = 0;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (cnt == 2) {
                    return false;
                } else if (cnt == 1) {
                    idx2 = i;
                } else {
                    idx1 = i;
                }
                cnt++;
            }
        }
        return cnt == 2 && s1.charAt(idx1) == s2.charAt(idx2) && s1.charAt(idx2) == s2.charAt(idx1);
    }

    public static void main(String[] args) {
        System.out.println(areAlmostEqual("1234", "1243"));
    }
}
