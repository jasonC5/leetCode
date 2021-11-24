package com.jason.leetCode;

public class LC423 {
    private static String[] EN = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static int[] SEQUENCE = {0, 6, 7, 8, 5, 4, 9, 2, 3, 1};

    public String originalDigits(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            count[aChar - 'a']++;
        }
        int[] visit = new int[10];
        for (int num : SEQUENCE) {
            char[] numChars = EN[num].toCharArray();
            int frequency = find(count, numChars);
            if (frequency > 0) {
                sub(count, numChars, frequency);
                visit[num] = frequency;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (visit[i] > 0){
                for (int j = 0; j < visit[i]; j++) {
                    ans.append(i);
                }
            }
        }
        return ans.toString();
    }

    private void sub(int[] count, char[] numChars, int frequency) {
        for (char c : numChars) {
            count[c - 'a'] -= frequency;
        }
    }

    private int find(int[] count, char[] numChars) {
        int[] numCharCount = new int[26];
        for (char c : numChars) {
            numCharCount[c - 'a']++;
        }
        int frequency = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (numCharCount[i] > 0){
                frequency = Math.min(frequency, count[i]/numCharCount[i]);
                if (frequency == 0) {
                    return 0;
                }
            }

        }
        return frequency;
    }

    public static void main(String[] args) {
        LC423 lc423 = new LC423();
        System.out.println(lc423.originalDigits("zerozero"));
    }
}
