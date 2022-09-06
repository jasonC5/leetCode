package com.jason.leetCode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author chenjieaj
 * @date 2022/9/6 9:01:52
 * @description
 */
public class LC828 {
    // 整体思路，找前面一样的，和后面一样的

    // 有序表
    public static int uniqueLetterString(String s) {
        int n = s.length();
        TreeSet<Integer>[] sortOrders = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            sortOrders[i] = new TreeSet<>();
            sortOrders[i].add(-1);
            sortOrders[i].add(n);
        }
        for (int i = 0; i < n; i++) {
            sortOrders[s.charAt(i) - 'A'].add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            TreeSet<Integer> sortOrder = sortOrders[s.charAt(i) - 'A'];
            ans += (i - sortOrder.floor(i - 1)) * (sortOrder.ceiling(i + 1) - i);
        }
        return ans;
    }

    // 栈
    public static int uniqueLetterString2(String s) {
        int n = s.length(), ans = 0;
        int[] pre = new int[n], post = new int[n], preIdx = new int[26], postIdx = new int[26];
        Arrays.fill(preIdx, -1);
        Arrays.fill(postIdx, n);
        for (int i = 0; i < n; i++) {
            pre[i] = preIdx[s.charAt(i) - 'A'];
            preIdx[s.charAt(i) - 'A'] = i;
            post[n - i - 1] = postIdx[s.charAt(n - i - 1) - 'A'];
            postIdx[s.charAt(n - i - 1) - 'A'] = n - i - 1;
        }
        for (int i = 0; i < n; i++) {
            ans += (i - pre[i]) * (post[i] - i);
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "ABC";
        System.out.println(uniqueLetterString2(s));
    }
}
