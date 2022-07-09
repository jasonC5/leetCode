package com.jason.leetCode;

public class LC1022 {
    public int sumRootToLeaf(TreeNode root) {
        return process(root, 0);
    }

    private int process(TreeNode node, int pre) {
        if (node == null) {
            return 0;
        }
        pre = pre << 1 | node.val;
        if (node.left == null && node.right == null) {
            //叶子节点
            return pre;
        }
        return process(node.left, pre) + process(node.right, pre);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        System.out.println(new LC1022().sumRootToLeaf(root));
    }
}
