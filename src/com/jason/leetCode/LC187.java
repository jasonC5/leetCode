package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC187 {

    public static List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        Set<String> ans = new HashSet<>();
        Set<String> exists = new HashSet<>();
        for (int i = 10; i <= s.length(); i++) {
            String sub = s.substring(i - 10, i);
            if (exists.contains(sub)){
                ans.add(sub);
            } else {
                exists.add(sub);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        String s = "AAAAAAAAAAA";
        System.out.println(findRepeatedDnaSequences(s));
    }

}
