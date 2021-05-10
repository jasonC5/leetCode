package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 *
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *  
 *
 * 示例 1：
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 示例 3：
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 示例 4：
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/leaf-similar-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


//Definition for a binary tree node.
class TreeNode {
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


public class LC872 {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leafA = new ArrayList<>();
            if (root1!=null) {
                dfs(root1, leafA);
            }
            List<Integer> leafB = new ArrayList<>();
            if (root2!=null) {
                dfs(root2, leafB);
            }
//            String strA = leafA.stream().map(x -> x + "").collect(Collectors.joining(","));
//            String strB = leafB.stream().map(x -> x + "").collect(Collectors.joining(","));
//            return strA.equals(strB);
            return leafA.equals(leafB);
        }

        public void dfs(TreeNode root, List<Integer> leaf) {
            if (root.left == null && root.right == null){
                leaf.add(root.val);//leafNode
            } else {
                if (root.left != null) {
                    dfs(root.left, leaf);
                }
                if (root.right != null){
                    dfs(root.right, leaf);
                }
            }
        }
    }
}
