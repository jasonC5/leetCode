package com.jason.leetCode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode2 {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null && l2 == null){
                return null;
            }
            int a = l1 == null ? 0: l1.val;
            int b = l2 == null ? 0: l2.val;
            // int x = a+b;
            // int y = 0;
            // if(x >= 10) {
            //     x = x-10;
            //     y = 1;
            // }
            int x = (a+b)%10,y=(a+b)/10;
            ListNode c = new ListNode(x);
            ListNode next1 = l1 == null ? null : l1.next;
            ListNode next2 = l2 == null ? null : l2.next;
            if(y>0){//直接把进位加到下一个节点上，不用再加一个参数来处理进位
                if(next1==null && next2 ==null){
                    next1 = new ListNode(y);
                } else if(next1 != null) {
                    next1.val += y;
                } else if(next2 != null) {
                    next2.val += y;
                }
            }
            ListNode next = addTwoNumbers(next1,next2);
            c.next = next;
            return c;
        }
    }
}
