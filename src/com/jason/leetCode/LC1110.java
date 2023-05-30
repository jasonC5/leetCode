package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenjieaj
 * @date 2023/5/30 13:44:08
 * @description
 */
public class LC1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> delete = new HashSet<>();
        for (int i : to_delete) {
            delete.add(i);
        }
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, delete, ans, true);
        return ans;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> deleteSet, List<TreeNode> ans, boolean noParent) {
        if (node == null) {
            return null;
        }
        boolean delete = deleteSet.contains(node.val);
        if (noParent && !delete) {
            ans.add(node);
        }
        node.left = dfs(node.left, deleteSet, ans, delete);
        node.right = dfs(node.right, deleteSet, ans, delete);
        return delete ? null : node;
    }
}
