package com.jason.msjd;

import java.util.ArrayList;
import java.util.List;

public class MS_04_06 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        buildList(root, list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (p == list.get(i)) {
                if (i == size - 1) {
                    return null;
                } else {
                    return list.get(i + 1);
                }
            }
        }
        return null;
    }

    private void buildList(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        buildList(node.left, list);
        list.add(node);
        buildList(node.right, list);
    }
}
