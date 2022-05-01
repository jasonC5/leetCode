package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你root1 和 root2这两棵二叉搜索树。请你返回一个列表，其中包含两棵树中的所有整数并按 升序 排序。.
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 * 提示：
 * 每棵树的节点数在[0, 5000] 范围内
 * -105<= Node.val <= 105
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Administrator
 */
public class LC1305 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();
        preOrder(root1, num1);
        preOrder(root2, num2);
        List<Integer> ans = new ArrayList<>();
        int idx1 = 0, idx2 = 0, next;
        while (idx1 < num1.size() || idx2 < num2.size()) {
            if (idx1 == num1.size()) {
                next = num2.get(idx2++);
            } else if (idx2 == num2.size()) {
                next = num1.get(idx1++);
            } else if (num1.get(idx1) > num2.get(idx2)) {
                next = num2.get(idx2++);
            } else {
                next = num1.get(idx1++);
            }
            ans.add(next);
        }
        return ans;
    }

    public void preOrder(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        preOrder(node.left, ans);
        ans.add(node.val);
        preOrder(node.right, ans);
    }

    public static void main(String[] args) {
        LC1305 lc1305 = new LC1305();
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);
        System.out.println(lc1305.getAllElements(root1, root2));
    }
}
