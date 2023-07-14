package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/14 15:45:06
 * @description
 */
public class LC979 {
    public int distributeCoins(TreeNode root) {
        return process(root)[0];
    }

    private int[] process(TreeNode node) {// {自己以及子树已经产生花费cost，剩余/欠的点数rest}
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = process(node.left);
        int[] right = process(node.right);
        int rest = left[1] + right[1] + node.val - 1;
        int cost = left[0] + right[0] + Math.abs(rest);
        return new int[]{cost, rest};
    }
}
