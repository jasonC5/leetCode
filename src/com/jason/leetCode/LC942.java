package com.jason.leetCode;

/**
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 * <p>
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 * <p>
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 * 示例 3：
 * <p>
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/di-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Administrator
 */
public class LC942 {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int low = 0, high = n;
        for (int i = 0; i < n; i++) {
            ans[i] = s.charAt(i) == 'D' ? high-- : low++;
        }
        ans[n] = low;
        return ans;
    }
}
