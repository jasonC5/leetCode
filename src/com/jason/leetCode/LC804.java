package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LC804 {

    public static String[] MORSE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> morseStrSet = new HashSet<>();
        for (String word : words) {
            String morseStr = getMorseStr(word);
            morseStrSet.add(morseStr);
        }
        return morseStrSet.size();
    }

    private String getMorseStr(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(MORSE[c - 'a']);
        }
        return sb.toString();
    }
}
