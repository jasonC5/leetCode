package com.jason.leetCode;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author JasonC5
 */
public class LC2055 {

    // 题意理解错误   --先读完题啊！！
    public static int[] platesBetweenCandlesError(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[] preHelp = new int[length];
        for (int i = 1; i < length - 1; i++) {
            if (chars[i] == '*' && chars[i - 1] == '|' && chars[i + 1] == '|') {
                preHelp[i] = preHelp[i - 1] + 1;
            } else {
                preHelp[i] = preHelp[i - 1];
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if (end - start < 2) {
                ans[i] = 0;
            } else {
                ans[i] = preHelp[end - 1] - preHelp[start];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        String s = "**|**|***|";
//        int[][] queries = {{2, 5}, {5, 9}};
        String s = "***|**|*****|**||**|*";
        int[][] queries = {{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};
        System.out.println(Arrays.toString(platesBetweenCandles(s, queries)));
    }

    public static int[] platesBetweenCandles(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[] pre = new int[length];//有几条 |
        TreeSet<Integer> sortTable = new TreeSet();// | 的位置   有序表
        if (chars[0] == '|') {
            pre[0] = 1;
            sortTable.add(0);
        }
        for (int i = 1; i < length; i++) {
            if (chars[i] == '|') {
                pre[i] = pre[i - 1] + 1;
                sortTable.add(i);
            } else {
                pre[i] = pre[i - 1];
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if (end - start < 2) {
                ans[i] = 0;
            } else {
                Integer ceiling = sortTable.ceiling(start);
                Integer floor = sortTable.floor(end);
                if (ceiling == null || floor == null || floor - ceiling < 2) {
                    ans[i] = 0;
                } else {
                    int cur = floor - ceiling - 1;// 中间有多少位置
                    cur -= pre[floor - 1] - pre[ceiling]; // 减去中间的  |
                    ans[i] = cur;
                }
            }
        }
        return ans;
    }

}
