package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC919 {
    public static class TreeNode {
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

    static class CBTInserter {

        TreeNode root;
        Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue = new LinkedList<>();
            Queue<TreeNode> container = new LinkedList();
            container.add(root);
            while (!container.isEmpty()) {
                TreeNode poll = container.poll();
                if (poll.left == null || poll.right == null) {
                    queue.add(poll);
                }
                if (poll.left != null) {
                    container.add(poll.left);
                }
                if (poll.right != null) {
                    container.add(poll.right);
                }
            }
        }

        public int insert(int val) {
            TreeNode cur = new TreeNode(val);
            TreeNode parentNode = queue.peek();
            int parent = parentNode.val;
            if (parentNode.left == null) {
                parentNode.left = cur;
            } else {
                parentNode.right = cur;
                queue.poll();
            }
            queue.add(cur);
            return parent;
        }

        public TreeNode get_root() {
            return root;
        }
    }

    public static void main(String[] args) {

    }
}
