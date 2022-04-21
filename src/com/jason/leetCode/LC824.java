package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC824 {
    public static final Set<Character> vowels = new HashSet<Character>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
        add('A');
        add('E');
        add('I');
        add('O');
        add('U');
    }};

    public static String toGoatLatin(String sentence) {
        StringBuilder mee = new StringBuilder("ma");
        StringBuilder ans = new StringBuilder();
        Character start = null;
        boolean isStart = true;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (isStart) {
                if (vowels.contains(c)) {
                    start = null;
                    ans.append(c);
                } else {
                    start = c;
                }
                isStart = false;
            } else if (c == ' ') {
                isStart = true;
                if (start != null){
                    ans.append(start);
                }
                mee.append('a');
                ans.append(mee);
                ans.append(c);
            } else {
                ans.append(c);
            }
        }
        if (start != null){
            ans.append(start);
        }
        mee.append('a');
        ans.append(mee);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
    }
}
