package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC693 {
    public static boolean hasAlternatingBits(int n) {
        boolean flag = (n & 1) != 0;
        while (n != 0) {
            if (flag ^ (n & 1) != 0) {
                return false;
            }
            flag = (n & 1) == 0;
            n >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
        System.out.println(hasAlternatingBits(7));
        System.out.println(hasAlternatingBits(11));
    }
}
