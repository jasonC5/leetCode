package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC2049 {
    public static int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        int[] fstSon = new int[n];//第一颗子树的节点数
        int[] secSon = new int[n];//第二颗子树的节点数
        int[] out = new int[n];//出度
        // 分数 = 左子树count * 右子树count * （n - 1 - 左子树count - 右子树count）
        // 拓扑排序 根据出度自下向上
        for (int i = 1; i < parents.length; i++) {
            out[parents[i]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (out[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer leaf = queue.poll();//出度为0的点，当前的叶子节点
            int treeNodeCount = fstSon[leaf] + secSon[leaf] + 1;//自己下面整颗子树的节点数
            int p = parents[leaf];//父节点
            if (p == -1) {//0节点，最后一个节点
                break;
            }
            if (fstSon[p] > 0) {//第一个子节点已经有了，放到第二个子节点
                secSon[p] = treeNodeCount;
            } else {
                fstSon[p] = treeNodeCount;
            }
            if (--out[p] == 0) {
                queue.add(p);
            }
        }
        // 整个遍历完毕之后，每个节点的左右子树节点数量就算完了
        long max = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            long point = (long) Math.max(1, fstSon[i]) * Math.max(1, secSon[i]) * Math.max(1, n - fstSon[i] - secSon[i] - 1);
            if (point == max) {
                maxCount++;
            } else if (point > max) {
                max = point;
                maxCount = 1;
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] p = {-1, 2, 0, 2, 0};
        System.out.println(countHighestScoreNodes(p));
    }
}
