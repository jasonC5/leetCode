package com.jason.jingsai.competition281;

import java.util.ArrayList;
import java.util.List;

public class C6014 {
    public static String repeatLimitedString(String s, int repeatLimit) {
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        for (char c : chars) {
            count[c - 'a']++;
        }
        List<int[]> bucket = new ArrayList<>();
        for (int i = 25; i >= 0; i--) {// 从大到小的顺序
            if (count[i] != 0) {
                bucket.add(new int[]{i, count[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        int pre = -1, preCount = 0;
        while (!bucket.isEmpty()) {
            int[] first = bucket.get(0);
            if (preCount == repeatLimit && pre == first[0]) {
                // 不能再添加重复了，得往后一个
                if (bucket.size() == 1) {// 不能加了
                    return sb.toString();
                } else {
                    int[] sec = bucket.get(1);
                    if (--sec[1] == 0) {
                        bucket.remove(sec);
                    }
                    sb.append((char) ('a' + sec[0]));
                    pre = sec[0];
                    preCount = 1;

                }
            } else {
                if (--first[1] == 0) {
                    bucket.remove(first);
                }
                sb.append((char) ('a' + first[0]));
                if (pre == first[0]) {
                    preCount++;
                } else {
                    pre = first[0];
                    preCount = 1;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "aabababaaaaaa";
        int k = 2;
        System.out.println(repeatLimitedString(s, k));
    }
}
