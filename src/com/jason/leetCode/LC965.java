package com.jason.leetCode;

public class LC965 {

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

    int rootVal;

    public boolean isUnivalTree(TreeNode root) {
        rootVal = root.val;
        return process(root);
    }

    private boolean process(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.val != rootVal) {
            return false;
        }
        return process(node.left) && process(node.right);
    }
}
