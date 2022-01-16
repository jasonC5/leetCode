package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LC382 {
    class Solution {
        Integer[] values;
        int length;
        Random random;
        public Solution(ListNode head) {
            ListNode reader = head;
            List<Integer> list = new ArrayList<>();
            while (reader != null) {
                list.add(reader.val);
                reader = reader.next;
            }
            length = list.size();
            values = list.toArray(new Integer[0]);
            random = new Random();
        }

        public int getRandom() {
            return values[random.nextInt(length)];
        }
    }
    //TODO 蓄水池算法
}
