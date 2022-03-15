package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonC5
 */
public class LC51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] delegate = new int[n];
        process(ans, delegate, 0, n);
        return ans;
    }

    private void process(List<List<String>> ans, int[] delegate, int cur, int n) {
        if (cur == n) {
            // 收答案
            List<String> curAns = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(j == delegate[i] ? 'Q' : '.');
                }
                curAns.add(sb.toString());
            }
            ans.add(curAns);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (canPut(delegate, cur, i)) {
                delegate[cur] = i;
                process(ans, delegate, cur + 1, n);
            }
        }
    }

    private boolean canPut(int[] delegate, int cur, int i) {
        for (int j = 0; j < cur; j++) {
            if (i == delegate[j] || Math.abs(i - delegate[j]) == Math.abs(cur - j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new LC51().solveNQueens(4));
    }
}
