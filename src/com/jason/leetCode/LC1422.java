package com.jason.leetCode;

public class LC1422 {
    public static int maxScore(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] pre0 = new int[n];
        int[] post1 = new int[n];
        pre0[0] = chars[0] == '0' ? 1 : 0;
        post1[n - 1] = chars[n - 1] == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            pre0[i] = pre0[i - 1] + (chars[i] == '0' ? 1 : 0);
            post1[n - i - 1] = post1[n - i] + (chars[n - i - 1] == '1' ? 1 : 0);
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int cur = pre0[i] + post1[i + 1];
            ans = Math.max(cur, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "011101";
        System.out.println(maxScore(s));
    }
}
