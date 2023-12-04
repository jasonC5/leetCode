package com.jason.leetCode;


/**
 * @author chenjieaj
 * @date 2023/12/4 9:49:25
 * @description
 */
public class LC1038 {
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }
}
