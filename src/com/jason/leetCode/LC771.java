package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/24 9:34:22
 * @description
 */
public class LC771 {
    public static int numJewelsInStones(String jewels, String stones) {
        long delegate = 0L;
        for (int i = 0; i < jewels.length(); i++) {
            delegate |= 1L << ((int) (jewels.charAt(i) - 'A'));
        }
        int ans = 0;
        for (int i = 0; i < stones.length(); i++) {
            if ((delegate & (1L << ((int) (stones.charAt(i) - 'A')))) != 0L) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
    }
}
