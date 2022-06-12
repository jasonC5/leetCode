package com.jason.leetCode;

public class LC926 {
    public static int minFlipsMonoIncr(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[] dp0 = new int[length];
        int[] dp1 = new int[length];
        // 到当前位置，以0结束
        dp0[0] = chars[0] == '0' ? 0 : 1;
        // 到当前位置，以1结束
        dp1[0] = chars[0] == '1' ? 0 : 1;
        for (int i = 1; i < length; i++) {
            dp0[i] = dp0[i - 1] + (chars[i] == '0' ? 0 : 1);
            // 当前位置变成1的代价
            dp1[i] = chars[i] == '1' ? 0 : 1;
            // 前面可以是0，也可以是1，找最小代价
            dp1[i] += Math.min(dp0[i - 1], dp1[i - 1]);
        }
        return Math.min(dp0[length - 1], dp1[length - 1]);
    }

    public static void main(String[] args) {
        String s = "00110";
        System.out.println(minFlipsMonoIncr(s));
    }
}
