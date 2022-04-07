package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC796 {
    public static boolean rotateString0(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        } else if (s.equals(goal)) {
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            String cur = s.substring(i) + s.substring(0, i);
            if (cur.equals(goal)) {
                return true;
            }
        }
        return false;
    }

    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    public static void main(String[] args) {
        String s = "gcmbf", goal = "fgcmb";
        System.out.println(rotateString(s, goal));
    }
}
