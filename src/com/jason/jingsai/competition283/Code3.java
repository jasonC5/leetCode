package com.jason.jingsai.competition283;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti] 表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：
 *
 * 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 * 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 * 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
 *
 * 测试用例会保证可以构造出 有效 的二叉树。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * 输出：[50,20,80,15,17,19]
 * 解释：根节点是值为 50 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 * 示例 2：
 *
 *
 *
 * 输入：descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * 输出：[1,2,null,null,3,4]
 * 解释：根节点是值为 1 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 *
 *
 * 提示：
 *
 * 1 <= descriptions.length <= 104
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 105
 * 0 <= isLefti <= 1
 * descriptions 所描述的二叉树是一棵有效二叉树
 * @author JasonC5
 */
public class Code3 {

    public static class TreeNode {
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

    //                                        父  子  左子、右子
    public static TreeNode createBinaryTree(int[][] descriptions) {
        TreeNode root = null;
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        for (int[] rs : descriptions) {
            parentMap.put(rs[1], rs[0]);
            TreeNode parent = nodeMap.getOrDefault(rs[0], new TreeNode(rs[0]));
            TreeNode son = nodeMap.getOrDefault(rs[1], new TreeNode(rs[1]));
            if (rs[2] == 0) {
                parent.right = son;
            } else {
                parent.left = son;
            }
            nodeMap.put(rs[0], parent);
            nodeMap.put(rs[1], son);
        }
        for (Integer node : nodeMap.keySet()) {
            if (!parentMap.containsKey(node)) {
                root = nodeMap.get(node);
                return root;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[][] d = {{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}};
        TreeNode binaryTree = createBinaryTree(d);
        System.out.println(binaryTree);
    }
}
