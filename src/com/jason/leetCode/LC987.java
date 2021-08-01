package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 * <p>
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 * <p>
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 * <p>
 * 返回二叉树的 垂序遍历 序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC987 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(7);
        List<List<Integer>> lists = verticalTraversal(root);
        System.out.println(lists);
    }

    public static final int OFFSET = 16;
    public static final int MASK = (1 << OFFSET) - 1;

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, PriorityQueue<Integer>> container = new TreeMap<>();
        process(root, 0, 0, container);
        List<List<Integer>> ans = new ArrayList<>();
        for (PriorityQueue<Integer> heap : container.values()) {
            List<Integer> list = new ArrayList<>();
            while (!heap.isEmpty()) {
                list.add(heap.poll() & MASK);
            }
            ans.add(list);
        }
        return ans;
    }

    private static void process(TreeNode node, int level, int idx, TreeMap<Integer, PriorityQueue<Integer>> container) {
        if (node == null) {
            return;
        }
        PriorityQueue<Integer> list = container.getOrDefault(idx, new PriorityQueue<>());
        list.offer((level << OFFSET) | node.val);
        container.put(idx, list);
        process(node.left, level + 1, idx - 1, container);
        process(node.right, level + 1, idx + 1, container);
    }

}
