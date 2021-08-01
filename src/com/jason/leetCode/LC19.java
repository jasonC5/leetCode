package com.jason.leetCode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * @author JasonC5
 */
public class LC19 {

    public static class ListNode {
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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        int k = 2;
        head = removeNthFromEnd(head, k);
        System.out.println(head);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //删除倒数第几个节点，并返回新的头结点（删除的可能是头结点）
        ListNode pre = null;
        ListNode less = head;
        for (int i = 0; i < n; i++) {
            less = less.next;
        }
        while (less != null) {
            pre = pre == null ? head : pre.next;
            less = less.next;
        }
        if (pre == null) {//删除的是头结点
            return head.next;
        } else {//删的不是头结点
            pre.next = pre.next.next;
            return head;
        }
    }
}
