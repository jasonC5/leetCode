package com.jason.leetCode;

import java.util.PriorityQueue;

public class LC748 {

    class Info implements Comparable {
        String word;
        int index;

        public Info(String word, int index) {
            this.word = word;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            Info c = (Info) o;
            return this.word.length() == c.word.length() ? this.index - c.index : this.word.length() - c.word.length();
        }
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] countBucket = getCountBucket(licensePlate);
        PriorityQueue<Info> minHeap = new PriorityQueue<>();
        for (int i = 0; i < words.length; i++) {
            int[] wordBucket = getCountBucket(words[i]);
            if (contains(countBucket, wordBucket)) {
                minHeap.offer(new Info(words[i], i));
            }
        }
        return minHeap.poll().word;
    }

    private boolean contains(int[] countBucket, int[] wordBucket) {
        for (int i = 0; i < 26; i++) {
            if (countBucket[i] > wordBucket[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] getCountBucket(String str) {
        int[] bucket = new int[26];
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                bucket[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                bucket[c - 'A']++;
            }
        }
        return bucket;
    }

}
