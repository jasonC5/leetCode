package com.jason.leetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2022/10/25 9:05:05
 * @description
 */
public class LC934 {

    public static final int[] DIS = {-1, 0, 1, 0, -1};

    public static int shortestBridge(int[][] grid) {
        int n = grid.length;
        List<int[]> island = new ArrayList<int[]>();
        Queue<int[]> queue = new ArrayDeque<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 先把第一个岛找全
                    queue.offer(new int[]{i, j});
                    // 标记已访问
                    grid[i][j] = -1;
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        island.add(poll);
                        for (int k = 0; k < 4; k++) {
                            int nx = poll[0] + DIS[k];
                            int ny = poll[1] + DIS[k + 1];
                            if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 1) {
                                queue.offer(new int[]{nx, ny});
                                grid[nx][ny] = -1;
                            }
                        }
                    }
                    // 第一个岛找全了，BFS往外扩，直到找到第二个岛屿
                    for (int[] isl : island) {
                        queue.offer(isl);
                    }
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int h = 0; h < size; h++) {
                            int[] poll = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nx = poll[0] + DIS[k];
                                int ny = poll[1] + DIS[k + 1];
                                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                    if (grid[nx][ny] == 1) {
                                        return step;
                                    } else if (grid[nx][ny] == 0) {
                                        queue.offer(new int[]{nx, ny});
                                        grid[nx][ny] = -1;
                                    }
                                    // 第一座岛已经都是-1了，都进过queue，不用处理
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        System.out.println(shortestBridge(grid));
    }
}
