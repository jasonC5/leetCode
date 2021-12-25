package com.jason.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class LC1609 {

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

    public static boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int leveSize = queue.size();
            int preVal = (level & 1) == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < leveSize; i++) {
                TreeNode node = queue.pollFirst();
                int val = node.val;
                if ((level & 1) == 0 && ((val & 1) == 0 || val <= preVal)) {//偶数层，里面都是奇数，且需要递增
                    return false;
                } else if ((level & 1) == 1 && ((val & 1) == 1 || val >= preVal)) {//奇数层，里面都是偶数，且需要递减
                    return false;
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
                preVal = val;
            }
            level++;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);
        System.out.println(isEvenOddTree(root));
    }
}
