package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/4/12 14:25:03
 * @description
 */
public class LC1147 {
    public static int longestDecomposition(String text) {
        int n = text.length();
        int l = 0, r = n - 1;
        int ans = 1;
        for (int k = 1; l + k <= r - k + 1; k++) {
            if (check(text, l, r - k + 1, k)) {
                ans += 2;
                l = l + k;
                r = r - k;
                k = 0;
            }
        }
        if (l > r) {
            ans--;
        }
        return ans;
    }

    private static boolean check(String text, int i, int j, int k) {
        for (int x = 0; x < k; x++) {
            if (text.charAt(i + x) != text.charAt(j + x)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestDecomposition("elvtoelvto"));
    }


}
