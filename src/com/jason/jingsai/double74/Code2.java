package com.jason.jingsai.double74;

public class Code2 {
    // 贪心
    public static long maximumSubsequenceCount(String text, String pattern) {
        // 1.原来有多少个子序列
        // 2.有多少个p[1],有多少个p[2]
        char[] p = pattern.toCharArray();
        int length = text.length();
        int p1Count = 0;
        int p2Count = 0;
        long curCount = 0;
        int[] leftP1 = new int[length];
        for (int i = 0; i < length; i++) {
            p1Count += text.charAt(i) == p[0] ? 1 : 0;
            p2Count += text.charAt(i) == p[1] ? 1 : 0;
            leftP1[i] = i == 0 ? p1Count : (leftP1[i - 1] + (text.charAt(i) == p[0] ? 1 : 0));
        }
        // 本来有多少对
        for (int i = length - 1; i > 0; i--) {
            if (text.charAt(i) == p[1]) {
                curCount += leftP1[i - 1];
            }
        }
        return curCount + Math.max(p1Count, p2Count);
    }

    public static void main(String[] args) {
        System.out.println(maximumSubsequenceCount("abdcdbc", "ac"));
        System.out.println(maximumSubsequenceCount("aabb", "ab"));
        System.out.println(maximumSubsequenceCount("fwymvreuftzgrcrxczjacqovduqaiig", "yy"));
    }

}
