package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2023/5/26 10:58:12
 * @description
 */
public class LC1091 {
    public static final int[] NEXT = {-1, -1, 0, 1, 1, 0, -1, 1, -1};

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        } else if (n == 1) {
            return 1;
        }
        int step = 1;
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                visit.add((cur[0] << 10) | cur[1]);
                for (int k = 0; k < 8; k++) {
                    int nextX = cur[0] + NEXT[k];
                    int nextY = cur[1] + NEXT[k + 1];
                    if (nextX >= 0 && nextY >= 0 && nextX < n && nextY < n && !visit.contains((nextX << 10) | nextY) && grid[nextX][nextY] == 0) {
                        if (nextX == n - 1 && nextY == n - 1) {
                            return step;
                        }
                        q.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix2(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == n - 1 && y == n - 1) {
                return dist[x][y];
            }
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (x + dx < 0 || x + dx >= n || y + dy < 0 || y + dy >= n) { // 越界
                        continue;
                    }
                    if (grid[x + dx][y + dy] == 1 || dist[x + dx][y + dy] <= dist[x][y] + 1) { // 单元格值不为 0 或已被访问
                        continue;
                    }
                    dist[x + dx][y + dy] = dist[x][y] + 1;
                    queue.offer(new int[]{x + dx, y + dy});
                }
            }
        }
        return -1;
    }
}
