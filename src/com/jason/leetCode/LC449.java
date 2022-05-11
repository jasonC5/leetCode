package com.jason.leetCode;

/**
 * @author Administrator
 */
public class LC449 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // 后序遍历
            if (root == null) {
                return null;
            }
            String ans = "";
            String left = serialize(root.left);
            ans += left == null ? "" : (left + ",");
            String right = serialize(root.right);
            ans += right == null ? "" : (right + ",");
            ans += root.val;
            return ans;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || "".equals(data)) {
                return null;
            }
            String[] split = data.split(",");
            return process(split, 0, split.length - 1);
        }

        private TreeNode process(String[] split, int left, int right) {
            if (left > right) {
                return null;
            }
            int val = Integer.parseInt(split[right]);
            TreeNode root = new TreeNode(val);
            if (left == right) {
                return root;
            }
            int nextRight = left;
            while (Integer.parseInt(split[nextRight]) < val) {
                nextRight++;
            }
            root.left = process(split, left, nextRight - 1);
            root.right = process(split, nextRight, right - 1);
            return root;
        }
    }
}
