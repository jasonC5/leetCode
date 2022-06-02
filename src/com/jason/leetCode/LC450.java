package com.jason.leetCode;

public class LC450 {
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
//                左右都有，让右子树顶上来，然后把左子树挂到右子树的最左子节点的左节点上
                TreeNode right = root.right;
                TreeNode left = root.left;
                TreeNode rightLeft = right;
                while (rightLeft.left != null) {
                    rightLeft = rightLeft.left;
                }
                rightLeft.left = left;
                return right;
            }
        } else {
            root.left = deleteNode(root.left, key);
            root.right = deleteNode(root.right, key);
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        System.out.println(deleteNode(root, 3));
    }
}
