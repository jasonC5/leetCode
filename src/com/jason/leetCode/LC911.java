package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC911 {

    static class TopVotedCandidate {
        int[] rank;
        int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            this.times = times;
            int length = persons.length;
            rank = new int[length];
            //[票数][时间][人]
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((i1, i2) -> i2[0] == i1[0] ? i2[1] - i1[1] : i2[0] - i1[0]);//票数：从大到小， 时间：从大到小
            // 人 : 票
            Map<Integer, Integer> numMap = new HashMap<>();
            for (int i = 0; i < length; i++) {
                int num = numMap.getOrDefault(persons[i], 0);
                numMap.put(persons[i], num + 1);
                maxHeap.offer(new int[]{num + 1, times[i], persons[i]});
                rank[i] = maxHeap.peek()[2];
            }
        }

        public int q(int t) {
            // 二分
            int min = 0, max = times.length - 1;
            int idx = 0;
            while (min <= max) {
                int mid = min + ((max - min) >> 1);
                if (times[mid] <= t) {
                    idx = mid;
                    if (times[mid] == t) {
                        break;
                    }
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
            return rank[idx];
        }
    }

    public static void main(String[] args) {
        int[] persons = {0, 1, 1, 0, 0, 1, 0}, times = {0, 5, 10, 15, 20, 25, 30};
        TopVotedCandidate t = new TopVotedCandidate(persons, times);
        System.out.println(t.q(3));
        System.out.println(t.q(12));
        System.out.println(t.q(25));
        System.out.println(t.q(15));
        System.out.println(t.q(24));
        System.out.println(t.q(8));
    }

}
