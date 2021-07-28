package com.jason.leetCode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 *
 *
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC671 {

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

    public int findSecondMinimumValue(TreeNode root) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            heap.offer(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int cur = root.val;
        int idx = 1;
        while(!heap.isEmpty()){
            Integer val = heap.poll();
            if (val != cur && ++idx == 2) {//这个方法可以找到任意小的，只要改这个2
                return val;
            }
        }
        return -1;
    }

    public int findSecondMinimumValue2(TreeNode root){
        //深度优先
        //找到比root.val大的最小数字
        int first = root.val;
        return dfs(root, first, -1);
    }

    private int dfs(TreeNode node, int smallest, int maybe) {
        if (node == null) {
            return maybe;
        } else if (node.val != smallest) {
            return maybe == -1 ? node.val: Math.min(maybe,node.val);
        }
        int left = dfs(node.left, smallest, maybe);
        int right = dfs(node.right, smallest, maybe);
        return left == right ? left : Math.min(left == -1 ? Integer.MAX_VALUE: left, right == -1 ? Integer.MAX_VALUE: right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        System.out.println(new LC671().findSecondMinimumValue2(root));
    }


}
