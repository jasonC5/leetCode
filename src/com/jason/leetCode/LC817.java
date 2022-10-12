package com.jason.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2022/10/12 9:03:35
 * @description
 */
public class LC817 {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0, preLen = 0;
        ListNode read = head;
        while (read != null) {
            if (set.contains(read.val)) {
                preLen++;
            } else {
                if (preLen > 0){
                    ans++;
                }
                preLen = 0;
            }
            read = read.next;
        }
        return ans + (preLen > 0 ? 1 : 0);
    }
}
