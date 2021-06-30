package com.jason.offer;

import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

public class Offer37 {

    //      Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            doSerialize(root, sb);
            //去掉最后一个分号
            return sb.toString().substring(0, sb.length() - 1);
        }

        private void doSerialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
            } else {
                sb.append(root.val + ",");
                doSerialize(root.left, sb);
                doSerialize(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] split = data.split(",");
            Queue<TreeNode> q = new LinkedList<>();
            for (String node : split) {
                if (node.equals("#")) {
                    q.add(null);
                } else {
                    q.add(new TreeNode(Integer.valueOf(node)));
                }
            }
            TreeNode root = doDeserialize(q);
            return root;
        }

        private TreeNode doDeserialize(Queue<TreeNode> queue) {
            TreeNode node;
            if (queue == null || queue.isEmpty() || (node = queue.poll()) == null) {
                return null;
            }
            node.left = doDeserialize(queue);
            node.right = doDeserialize(queue);
            return node;
        }
        // Your Codec object will be instantiated and called as such:
        // Codec codec = new Codec();
        // codec.deserialize(codec.serialize(root));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        System.out.println(serialize);
        root = codec.deserialize(serialize);
        System.out.println(root);
    }
}
