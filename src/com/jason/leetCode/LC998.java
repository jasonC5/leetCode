package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/30 9:01:17
 * @description
 */
public class LC998 {

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

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root.val < val) {
            node.left = root;
            return node;
        }
        TreeNode parent = root, cur = root.right;
        while (cur != null && cur.val > val) {
            parent = cur;
            cur = cur.right;
        }
        parent.right = node;
        node.left = cur;
        return root;
    }
}
