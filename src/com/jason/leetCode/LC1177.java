package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/6/15 9:07:07
 * @description
 */
public class LC1177 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[] delegate = new int[n + 1];// 前缀和：每个二进制位代表一个字母，偶数为0，奇数为1
        for (int i = 1; i <= n; i++) {
            delegate[i] = delegate[i - 1] ^ (1 << (s.charAt(i - 1) - 'a'));
        }
        List<Boolean> ans = new ArrayList<>();
        int l, r, k, d, cnt;
        for (int[] query : queries) {
            l = query[0];
            r = query[1];
            k = query[2];
            d = delegate[l] ^ delegate[r + 1];
            cnt = 0;
            while (d != 0) {
                d &= d - 1;
                cnt++;
            }
            ans.add(k << 1 >= cnt - 1);
        }
        return ans;
    }
}
