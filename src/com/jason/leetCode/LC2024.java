package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonC5
 */
public class LC2024 {
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int length = answerKey.length();
        List<Integer> tList = new ArrayList<>();
        List<Integer> fList = new ArrayList<>();
        fList.add(-1);
        tList.add(-1);
        for (int i = 0; i < length; i++) {
            if (answerKey.charAt(i) == 'T') {
                tList.add(i);
            } else {
                fList.add(i);
            }
        }
        fList.add(length);
        tList.add(length);
        if (tList.size() - 2 <= k || fList.size() - 2 <= k) {
            return length;
        }
        int ans = 0;
        for (int i = k; i < tList.size() - 1; i++) {
            ans = Math.max(ans, tList.get(i + 1) - tList.get(i - k) - 1);
        }
        for (int i = k; i < fList.size() - 1; i++) {
            ans = Math.max(ans, fList.get(i + 1) - fList.get(i - k) - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String answerKey = "TF";
        int k = 2;
        System.out.println(maxConsecutiveAnswers(answerKey, k));

    }
}
