package com.jason.leetCode;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * 示例 2：
 *
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC938 {

    public static void main(String[] args) {

    }

    
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
        public int rangeSumBST(TreeNode root, int low, int high) {
            //当前节点为null，返回0
            //当前节点小于最小值，返回左子树和
            //当前节点大于最大值，返回右子树的和
            //当前节点再范围内，返回本节点+左子树+右子树
            if (root == null) {
                return 0;
            } else if (root.val < low){
                return rangeSumBST(root.right, low, high);
            } else if (root.val > high) {
                return rangeSumBST(root.left, low, high);
            } else {
                return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
            }

        }
    }
}
