package com.jason.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class LC503 {
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

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        process(root, sumCountMap);
        List<Integer> sortedNodeSumList = sumCountMap.keySet().stream().sorted((i1, i2) -> sumCountMap.get(i2) - sumCountMap.get(i1)).collect(Collectors.toList());
        int count = sumCountMap.get(sortedNodeSumList.get(0));
        List<Integer> ans = new ArrayList<>();
        for (Integer sum : sortedNodeSumList) {
            if (count == sumCountMap.get(sum)) {
                ans.add(sum);
            } else {
                break;
            }
        }
        return ans.stream().mapToInt(x -> x).toArray();
    }

    private int process(TreeNode node, Map<Integer, Integer> sumCountMap) {
        if (node == null) {
            return 0;
        }
        int left = process(node.left, sumCountMap);
        int right = process(node.right, sumCountMap);
        int sum = left + right + node.val;
        sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
