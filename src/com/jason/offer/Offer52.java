package com.jason.offer;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * @author JasonC5
 */
public class Offer52 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //双指针法
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        while (!(pointerA == null && pointerB == null)) {
            //先if再往后推，相当于走了两步
//            if (pointerA == null) {
//                pointerA = headB;
//            }
//            if (pointerB == null) {
//                pointerB = headA;
//            }
            if (pointerA == pointerB) {
                return pointerA;
            }
            pointerA = pointerA == null ? headB : pointerA.next;
            pointerB = pointerB == null ? headA : pointerB.next;
        }
        return null;
    }

}
