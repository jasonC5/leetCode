package com.jason.leetCode;

import java.util.Stack;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode newHead = null;
        ListNode pre = null;
        // 数组手写栈
        ListNode[] stack = new ListNode[k];
        int size = 0;
        ListNode reader = head;
        while (reader != null) {
            stack[size++] = reader;
            ListNode next = reader.next;
            //满了，倒出来
            if (size == k) {
                ListNode curHead = stack[size - 1];
                // 连接前面的
                if (pre == null) {
                    newHead = curHead;
                } else {
                    pre.next = curHead;
                }
                pre = stack[0];//栈底会成为最后一个元素
                //内部调整next指针
                while (size > 0) {
                    ListNode cur = stack[--size];
                    cur.next = size == 0 ? next : stack[size - 1];
                }
            }
            // 往后跳
            reader = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = reverseKGroup(head, 2);
        System.out.println(newHead);
    }

}
