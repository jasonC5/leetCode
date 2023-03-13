package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/10 17:27:56
 * @description
 */
public class LC2379 {
    public int minimumRecolors(String blocks, int k) {
        int ans = 0, bNum = 0, n = blocks.length();
        for (int i = 0; i < k; i++) {
            bNum += blocks.charAt(i) == 'W' ? 1 : 0;
        }
        ans = bNum;
        for (int i = k; i < n; i++) {
            bNum += blocks.charAt(i) == 'W' ? 1 : 0;
            bNum -= blocks.charAt(i - k) == 'W' ? 1 : 0;
            ans = Math.min(ans, bNum);
        }
        return ans;
    }
}
