package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
 * <p>
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值0 <= node.val <= 500。
 * 目标结点target是树上的结点。
 * 0 <= K <= 1000.
 *
 * @author JasonC5
 */
public class LC863 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        root.right = new TreeNode(1);
        TreeNode target = root.right.left = new TreeNode(3);
        System.out.println(distanceK(root, target, 3));
    }


    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //从target往下，自己深度为k的子节点个数
        //从target往上一层，另外一个子节点，k-1 深度的节点
        //再往上一层，另外一个子节点，k-2 深度的节点
        //问题1：如何网上走，找到自己的父亲节点
        //问题2：如何找到k，然后整理出来k的父节点链
        //--深度遍历整棵树，获得所有节点的父节点的指针
        //第一步，先找到target的深度，和从root往下所有祖先节点，从target开始，往上遍历
        Map<TreeNode, TreeNode> parentIdxMap = new HashMap<>();
        parentIdxMap.put(root, null);
        deepFirstTraverse(root, parentIdxMap);
        //拿到了整棵树的头结点
        List<Integer> ans = findAns(parentIdxMap, target, null, k, 0);
        return ans;
    }

    private static List<Integer> findAns(Map<TreeNode, TreeNode> parentIdxMap, TreeNode node, TreeNode from, int k, int path) {
        List<Integer> ans = new ArrayList<>();
        if (node == null) {
            return null;
        }
        if (path == k) {
            ans.add(node.val);
            return ans;
        }
        if (node.left != from) {
            List<Integer> ans1 = findAns(parentIdxMap, node.left, node, k, path + 1);
            if (ans1 != null) {
                ans.addAll(ans1);
            }
        }
        if (node.right != from) {
            List<Integer> ans1 = findAns(parentIdxMap, node.right, node, k, path + 1);
            if (ans1 != null) {
                ans.addAll(ans1);
            }
        }
        TreeNode parent = parentIdxMap.get(node);
        if (parent != from) {
            List<Integer> ans1 = findAns(parentIdxMap, parent, node, k, path + 1);
            if (ans1 != null) {
                ans.addAll(ans1);
            }
        }
        return ans;
    }


    private static void deepFirstTraverse(TreeNode node, Map<TreeNode, TreeNode> parentIdxMap) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parentIdxMap.put(node.left, node);
            deepFirstTraverse(node.left, parentIdxMap);
        }
        if (node.right != null) {
            parentIdxMap.put(node.right, node);
            deepFirstTraverse(node.right, parentIdxMap);
        }
    }
}
