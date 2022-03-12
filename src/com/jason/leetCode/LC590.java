package com.jason.leetCode;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class LC590 {
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

    ;

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        process(root, list);
        return list;
    }

    private void process(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.children != null && node.children.size() > 0) {
            for (Node child : node.children) {
                process(child, list);
            }
        }
        list.add(node.val);
    }

    public static List<Integer> postorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node first = stack.pop();
            ans.add(first.val);
            if (first.children != null) {
                for (int i = 0; i < first.children.size(); i++) {
                    stack.push(first.children.get(i));
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
