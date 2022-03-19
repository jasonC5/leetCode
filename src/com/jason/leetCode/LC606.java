package com.jason.leetCode;

public class LC606 {
    public String tree2str(TreeNode root) {
        if(root == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        if(left ==null && right == null){
            return sb.toString();
        }
        if(left != null){
            sb.append("(").append(left).append(")");
        } else {
            sb.append("()");
        }
        if(right != null){
            sb.append("(").append(right).append(")");
        }
        return sb.toString();
    }
}
