package com.jason.leetCode;

import java.util.List;

public class LC559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public int maxDepth(Node root) {
        return deep(root, 1);
    }

    private int deep(Node root, int deep) {
        if (root.children == null || root.children.size() == 0) {
            return deep;
        }
        int ans = deep;
        for (Node child : root.children) {
            ans = Math.max(ans, deep(child, deep + 1));
        }
        return ans;
    }


}
