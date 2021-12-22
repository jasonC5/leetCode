package com.jason.leetCode;

public class LC686 {

    public static int repeatedStringMatch(String a, String b) {
        if (b == null || b.length() == 0) {
            return 0;
        }
        StringBuilder tmp = new StringBuilder(a);
        while (tmp.length() < b.length()) {
            tmp.append(a);
        }
        if (tmp.indexOf(b) != -1) {
            return tmp.length() / a.length();
        } else {
            tmp.append(a);
            return tmp.indexOf(b) == -1 ? -1 : (tmp.length() / a.length());
        }
    }

    public static void main(String[] args) {
//        String a = "abcd", b = "cdabcdab";
//        String a = "a", b = "aa";
//        String a = "a", b = "a";
        String a = "abc", b = "wxyz";
        System.out.println(repeatedStringMatch(a, b));
    }

}
