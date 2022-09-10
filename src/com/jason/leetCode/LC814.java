package com.jason.leetCode;

public class LC814 {
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

    public TreeNode pruneTree(TreeNode root) {
        boolean flag = process(root);
        return flag ? root : null;
    }

    private boolean process(TreeNode node) {
        boolean flag = node.val == 1;
        if (node.left != null){
            boolean left = process(node.left);
            flag |= left;
            if (!left){
                node.left = null;
            }
        }
        if (node.right != null){
            boolean right = process(node.right);
            flag |= right;
            if (!right){
                node.right = null;
            }
        }
        return flag;
    }
}
