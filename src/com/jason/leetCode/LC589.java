package com.jason.leetCode;

import java.util.*;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author JasonC5
 */
public class LC589 {
    class Node {
        public int val;
        public List<Node> children;
        public Node() {
        }
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 双端队列
    public static List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(root);
        while (!deque.isEmpty()){
            Node first = deque.pollFirst();
            ans.add(first.val);
            if (first.children != null){
                for (int i = first.children.size()-1; i >=0 ; i--) {
                    deque.addFirst(first.children.get(i));
                }
            }
        }
        return ans;
    }
    // 栈
    public static List<Integer> preorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node first = stack.pop();
            ans.add(first.val);
            if (first.children != null){
                for (int i = first.children.size()-1; i >=0 ; i--) {
                    stack.push(first.children.get(i));
                }
            }
        }
        return ans;
    }

}
