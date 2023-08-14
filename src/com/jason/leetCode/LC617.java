package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/8/14 14:21:16
 * @description
 */
public class LC617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        int value = (root1 == null ? 0 : root1.val) + (root2 == null ? 0 : root2.val);
        TreeNode node = root1 == null ? root2 : root1;
        node.val = value;
        node.left = mergeTrees(root1 == null ? null : root1.left, root2 == null ? null : root2.left);
        node.right = mergeTrees(root1 == null ? null : root1.right, root2 == null ? null : root2.right);
        return node;
    }
}
