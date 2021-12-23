package com.jason.leetCode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * <p>
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 * <p>
 * 输入：s = "abcd"
 * 输出：""
 *
 * <p>
 * 提示：
 * <p>
 * 2 <= s.length <= 3 * 104
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1044 {
    public String longestDupSubstring1(String s) {
        int length = s.length();
        int l = 0, r = length - 1;
        String ans = "";
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            String tmp = null;
            if ((tmp = check1(s, mid)) != null) {
                ans = tmp;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // 内存超了
    private String check1(String s, int len) {
        Set<String> existed = new HashSet<>();
        for (int i = 0; i <= s.length() - len; i++) {
            String subStr = s.substring(i, i + len);
            if (existed.contains(subStr)) {
                return subStr;
            } else {
                existed.add(subStr);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LC1044 lc1044 = new LC1044();
        String s = "banana";
        System.out.println(lc1044.longestDupSubstring(s));
    }

    public String longestDupSubstring(String s) {
        int length = s.length();
        // 映射成int数组
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = s.charAt(i) - 'a';
        }
        // 生成两个进制
        Random random = new Random();
        int a1 = random.nextInt(75) + 26;
        int a2 = random.nextInt(75) + 26;
        // 生成两个模
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int l = 0, r = length - 1;
//        String ans = "";
        int start = -1, maxLen = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
//            String tmp = null;
            int tmp = check(a, mid, a1, a2, mod1, mod2);
            if (tmp != -1) {// 有，记录length和start
                start = tmp;
                maxLen = mid;
                l = mid + 1;
            } else {// 没有
                r = mid - 1;
            }
        }
        return start == -1 ? "" : s.substring(start, start + maxLen);
    }

    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }
        }
        // 存储一个编码组合是否出现过
        Set<Long> seen = new HashSet<Long>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - m; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }

            long num = h1 * mod2 + h2;
            // 如果重复，则返回重复串的起点
            if (!seen.add(num)) {
                return start;
            }
        }
        // 没有重复，则返回-1
        return -1;
    }

    public long pow(int a, int m, int mod) {
        long ans = 1;
        long contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) {
                contribute += mod;
            }
            m /= 2;
        }
        return ans;
    }
}
