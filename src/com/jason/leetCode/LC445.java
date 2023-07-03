package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author chenjieaj
 * @date 2023/7/3 9:03:39
 * @description
 */
public class LC445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null, next = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            int cur = x + y + carry;
            next = head;
            head = new ListNode(cur > 9 ? cur - 10 : cur);
            head.next = next;
            carry = cur > 9 ? 1 : 0;
        }
        return head;
    }
}
