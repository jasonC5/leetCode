package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC94 {
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private void process(TreeNode node, List<Integer> ans) {
        if (node == null){
            return;
        }
        process(node.left, ans);
        ans.add(node.val);
        process(node.right, ans);
    }


}
