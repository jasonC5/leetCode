package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2022/6/22 9:02:07
 * @description
 */
public class LC513 {
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

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int left = root.val;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            left = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return left;
    }
}
