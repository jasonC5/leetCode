package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenjieaj
 * @date 2023/4/10 11:05:45
 * @description
 */
public class LC1019 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        Stack<int[]> stack = new Stack<>();
        List<Integer> ans = new ArrayList<Integer>();
        ListNode cur = head;
        int idx = -1;
        while(cur != null){
            ++idx;
            ans.add(0);
            while(!stack.isEmpty() && cur.val > stack.peek()[0]){
                ans.set(stack.pop()[1], cur.val);
            }
            stack.push(new int[]{cur.val, idx});
            cur = cur.next;
        }
        int[] ints = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ints[i] = ans.get(i);
        }
        return ints;
    }
}
