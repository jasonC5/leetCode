package com.jason.leetCode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 *
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author JasonC5
 */
public class LC203 {

    public static void main(String[] args) {

    }
    //删除链表中指定元素
    public static ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val){
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode prov = head;
        ListNode point = head.next;
        while (point != null){
            if (point.val == val) {
                prov.next = point.next;
            } else {
                prov = point;
            }
            point = point.next;
        }
        return head;
    }
}
