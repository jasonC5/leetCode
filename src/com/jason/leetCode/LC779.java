package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/20 9:13:23
 * @description
 */
public class LC779 {
    public static final int[] ans3 = {0, 1, 1, 0};

    public static int kthGrammar(int n, int k) {
        if (n < 4) {
            return ans3[k - 1];
        }
        if (k <= (1 << (n - 2))) {
            return kthGrammar(n - 1, k);
        } else {
            return 1 - kthGrammar(n - 1, k - (1 << (n - 2)));
        }
    }

    public static void main(String[] args) {
        System.out.println(kthGrammar(30, 434991989));
    }
}
