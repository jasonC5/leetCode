package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LC897 {


//      Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public TreeNode increasingBST(TreeNode root) {
            List<Integer> list = new ArrayList();
            //先按中序遍历读
            read(root,list);
            //再将读过来的组装成只有右子节点的树
            TreeNode result = buildNewTree(list);
            return result;
        }

        public TreeNode buildNewTree(List<Integer> list){
            TreeNode result = null;
            TreeNode pointer = null;
            for(Integer num: list) {
                if(result == null) {
                    result = new TreeNode(num);
                    pointer = result;
                } else {
                    TreeNode newNode = new TreeNode(num);
                    pointer.right = newNode;
                    pointer = newNode;
                }
            }
            return result;
        }

        public void read(TreeNode node, List<Integer> list){
            if(node.left != null){
                read(node.left, list);
            }
            list.add(node.val);
            if(node.right != null){
                read(node.right, list);
            }
        }
    }
}
