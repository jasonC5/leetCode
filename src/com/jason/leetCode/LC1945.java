package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/15 11:19:28
 * @description
 */
public class LC1945 {
    public static int getLucky(String s, int k) {
        String num = getNum(s);
        for (int i = 0; i < k; i++) {
            num = getNumSum(num);
        }
        return Integer.parseInt(num);
    }

    private static String getNumSum(String num) {
        int ans = 0;
        for (int i = 0; i < num.length(); i++) {
            ans += num.charAt(i) - '0';
        }
        return ans + "";
    }

    private static String getNum(String s) {
        StringBuilder num = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            num.append(s.charAt(i) - 'a' + 1);
        }
        return num.toString();
    }

    public static void main(String[] args) {
        System.out.println(getLucky("leetcode", 2));
    }


}
