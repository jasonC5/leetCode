package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/2 9:10:47
 * @description
 */
public class LC687 {
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

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        process(root);
        return max;
    }

    private int process(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        int length = 0, lengthLeft = 0, lengthRight = 0;
        if (node.left != null) {
            lengthLeft = process(node.left);
            if (node.val == node.left.val) {
                length = Math.max(length, lengthLeft + 1);
                max = Math.max(max, lengthLeft + 1);
            }
        }
        if (node.right != null) {
            lengthRight = process(node.right);
            if (node.val == node.right.val) {
                length = Math.max(length, lengthRight + 1);
                max = Math.max(max, lengthRight + 1);
            }
        }
        if (node.left != null && node.right != null && node.val == node.left.val && node.val == node.right.val) {
            max = Math.max(max, lengthLeft + lengthRight + 2);
        }
        return length;
    }
}
