package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2022/8/22 9:20:59
 * @description
 */
public class LC655 {

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

    static List<List<String>> ans;
    static int height = -1;

    public static List<List<String>> printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            height++;
            int size = queue.size();
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
        ans = new ArrayList<>();
        int columns = (1 << (height + 1)) - 1;
        for (int i = 0; i <= height; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                cur.add("");
            }
            ans.add(cur);
        }
        dfs(root, 0, (columns - 1) >> 1);
        return ans;
    }

    private static void dfs(TreeNode node, int row, int col) {
        if (node == null) {
            return;
        }
        ans.get(row).set(col, String.valueOf(node.val));
        dfs(node.left, row + 1, col - (1 << (height - row - 1)));
        dfs(node.right, row + 1, col + (1 << (height - row - 1)));
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(printTree(root));
    }
}
