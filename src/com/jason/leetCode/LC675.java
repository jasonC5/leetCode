package com.jason.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author JasonC5
 */
public class LC675 {
    public static final int[] LOCATION = new int[]{1, 0, -1, 0, 1};

    private int row, col;

    public int cutOffTree(List<List<Integer>> forest) {
        row = forest.size();
        col = forest.get(0).size();
        List<int[]> treeList = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            List<Integer> list = forest.get(i);
            for (int j = 0; j < list.size(); j++) {
                // 这是一棵树
                if (list.get(j) > 1) {
                    int[] info = new int[]{i, j, list.get(j)};
                    treeList.add(info);
                }
            }
        }
        // 排序，按照树的高度
        treeList.sort((t1, t2) -> t1[2] - t2[2]);
        // 起始位置 0,0
        int preX = 0, preY = 0, ans = 0;
        // 高度从低到高
        for (int[] tree : treeList) {
            // 步数
            int step = dijkstra(forest, preX, preY, tree[0], tree[1]);
            if (step == -1) {
                return -1;
            }
            ans += step;
            preX = tree[0];
            preY = tree[1];
        }
        return ans;
    }


    private int dijkstra(List<List<Integer>> forest, int preX, int preY, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        int step = 0;
        queue.add(new int[]{preX, preY});
        while (!queue.isEmpty()) {
            // 按层遍历
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                // 走到了，返回步数
                if (poll[0] == x && poll[1] == y) {
                    return step;
                }
                // 没找到，上下左右加入queue
                for (int k = 0; k < 4; k++) {
                    int nextX = poll[0] + LOCATION[k];
                    int nextY = poll[1] + LOCATION[k + 1];
                    // 没有越界 + 能走 + 没来过
                    if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < col && forest.get(nextX).get(nextY) != 0 && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        LC675 obj = new LC675();
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(new ArrayList<Integer>(){{this.add(1);this.add(2);this.add(3);}});
        forest.add(new ArrayList<Integer>(){{this.add(0);this.add(0);this.add(4);}});
        forest.add(new ArrayList<Integer>(){{this.add(7);this.add(6);this.add(5);}});
        System.out.println(obj.cutOffTree(forest));
    }
}
