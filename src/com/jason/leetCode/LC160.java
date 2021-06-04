package com.jason.leetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author JasonC5
 */
public class LC160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> setA = new HashSet();
        ListNode point = headA;
        while(point != null){
            setA.add(point);
            point = point.next;
        }
        point = headB;
        while(point != null){
            if (setA.contains(point)) {
                return point;
            }
            point = point.next;
        }
        return null;
    }
    //双指针法更快，空间复杂度更低

    /**
     * 两种情况：
     * 1. a b 相交，假设交点后方长度为z，a的交点前长度为x，b的交点前长度为y
     *      若x=y，则两个指针同时指到交点，返回交点
     *      若x!=y，则两个指针第一遍不相遇，a指针遍历完后指向b的头部，b指针遍历完后指向a的头部，那么，在循环到（x+y+z）步的时候，两个指针必定相遇，返回交点
     * 2.a b不想交，a的长度为x，b的长度为y
     *      若x=y，则两个指针同时跑完指向空，null==null，返回null
     *      若x!=y，a指针跑完a指向b，b指针跑完b指向a，在循环到x+y步的时候，两个指针跑完了两个链表，都到达了链表尾部的null，null==null返回null
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //双指针法
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;

    }

}

// * Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
