package com.jason.leetCode;

public class LC717 {
    public static boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) {
            return true;
        } else if (bits.length == 2) {
            return bits[0] == 0;
        }
        int idx = 0;
        while (idx < bits.length - 1) {
            idx += bits[idx] == 0 ? 1 : 2;
        }
        return idx == bits.length - 1;
    }

    public static void main(String[] args) {
        int[] bits = new int[]{1, 1, 1, 0};
        System.out.println(isOneBitCharacter(bits));
    }
}
