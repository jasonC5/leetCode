package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2022/8/5 9:13:52
 * @description
 */
public class LC623 {
    public class TreeNode {
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

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int deep = 2;
        while (deep < depth) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            deep++;
        }
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(val);
            node.left.left = left;
            node.right = new TreeNode(val);
            node.right.right = right;
        }
        return root;
    }
}
