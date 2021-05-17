package com.jason.leetCode;

public class LC993 {

//     * Definition for a binary tree node.
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
    class Solution {
        TreeNode xNode,yNode;
        TreeNode xParent,yParent;
        int xdeep, ydeep,x,y;

        public boolean isCousins(TreeNode root, int x, int y) {
            this.x = x;
            this.y = y;
            dfs(root, 0, null);
            return (xdeep==ydeep)&&(xParent!=yParent);
        }

        private void dfs(TreeNode thisNode, int deep, TreeNode parent) {
            if (thisNode == null) {
                return;
            }
            if (thisNode.val == x){
                xNode = thisNode;
                xdeep = deep;
                xParent = parent;
            } else if (thisNode.val == y) {
                yNode = thisNode;
                ydeep = deep;
                yParent = parent;
            }
            if (xNode!=null && yNode!=null) {
                return;
            }
            dfs(thisNode.left,deep+1,thisNode);
            if (xNode!=null && yNode!=null) {
                return;
            }
            dfs(thisNode.right,deep+1,thisNode);
            if (xNode!=null && yNode!=null) {
                return;
            }
        }

    }
}
