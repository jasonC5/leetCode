package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2022/9/5 9:16:47
 * @description
 */
public class LC652 {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> nodeMap = new HashMap<>();
        Set<String> inAns = new HashSet<>();
        List<TreeNode> ans = new ArrayList<>();
        process(root, nodeMap, inAns, ans);
        return ans;
    }

    private String process(TreeNode node, Map<String, TreeNode> nodeMap, Set<String> inAns, List<TreeNode> ans) {
        if (node == null) {
            return "N";
        }
        String left = process(node.left, nodeMap, inAns, ans);
        String right = process(node.right, nodeMap, inAns, ans);
        String cur = node.val + "L" + left + "R" + right;
        if (nodeMap.containsKey(cur) && !inAns.contains(cur)) {
            ans.add(node);
            inAns.add(cur);
        }
        nodeMap.put(cur, node);
        return cur;
    }
}
