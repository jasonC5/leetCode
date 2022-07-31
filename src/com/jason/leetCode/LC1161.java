package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1161 {
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

    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE, cur = 1, level = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int curSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                curSum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            if (curSum > max) {
                max = curSum;
                level = cur;
            }
            cur++;
        }
        return level;
    }
}
