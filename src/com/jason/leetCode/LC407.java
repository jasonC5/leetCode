package com.jason.leetCode;

import java.util.PriorityQueue;

/**
 * 给你一个m x n的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * 示例2:
 * <p>
 * <p>
 * <p>
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 * <p>
 * <p>
 * 提示:
 * <p>
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC407 {
    // 辅助数组，但是会互相影响，无法准确得出答案
    public static int trapRainWaterError(int[][] heightMap) {
        int row_len = heightMap.length;
        int col_len = heightMap[0].length;
        //辅助数组
        int[][] topMax = new int[row_len][col_len];
        int[][] bottomMax = new int[row_len][col_len];
        int[][] leftMax = new int[row_len][col_len];
        int[][] rightMax = new int[row_len][col_len];
        for (int col = 0; col < col_len; col++) {
            for (int row = 0; row < row_len; row++) {
                topMax[row][col] = row == 0 ? heightMap[row][col] : Math.max(heightMap[row][col], topMax[row - 1][col]);
                leftMax[row][col] = col == 0 ? heightMap[row][col] : Math.max(heightMap[row][col], leftMax[row][col - 1]);
            }
        }
        for (int row = row_len - 1; row >= 0; row--) {
            for (int col = col_len - 1; col >= 0; col--) {
                bottomMax[row][col] = row == row_len - 1 ? heightMap[row][col] : Math.max(heightMap[row][col], bottomMax[row + 1][col]);
                rightMax[row][col] = col == col_len - 1 ? heightMap[row][col] : Math.max(heightMap[row][col], rightMax[row][col + 1]);
            }
        }
        //TODO 思路错误
        for (int col = 0; col < col_len; col++) {
            for (int row = 0; row < row_len; row++) {
                topMax[row][col] = row == 0 ? heightMap[row][col] : Math.max(heightMap[row][col], topMax[row - 1][col]);
                leftMax[row][col] = col == 0 ? heightMap[row][col] : Math.max(heightMap[row][col], leftMax[row][col - 1]);
            }
        }
        int ans = 0;
        for (int row = 1; row < row_len - 1; row++) {
            for (int col = 1; col < col_len - 1; col++) {
                int min = Math.min(Math.min(topMax[row - 1][col], bottomMax[row + 1][col]), Math.min(leftMax[row][col - 1], rightMax[row][col + 1]));
                ans += Math.max(0, min - heightMap[row][col]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] heightMap = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
//        int[][] heightMap = {{12, 13, 1, 12}, {13, 4, 13, 12}, {13, 8, 10, 12}, {12, 13, 12, 12}, {13, 13, 13, 13}};
        int[][] heightMap = {{5, 5, 5, 1}, {5, 1, 1, 5}, {5, 1, 5, 5}, {5, 2, 5, 8}};
        System.out.println(trapRainWater(heightMap));
        System.out.println(trapRainWater2(heightMap));
    }

    public static class Info {
        int row;
        int col;
        int value;

        public Info(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    // 小根堆
    public static int trapRainWater(int[][] heightMap) {
        int row = heightMap.length;
        int col = heightMap[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<Info> heap = new PriorityQueue<>((i1, i2) -> i1.value - i2.value);
        //先把最外面一圈放进去
        for (int i = 0; i < col; i++) {
            heap.offer(new Info(0, i, heightMap[0][i]));
            heap.offer(new Info(row - 1, i, heightMap[row - 1][i]));
            visited[0][i] = true;
            visited[row - 1][i] = true;
        }
        for (int i = 1; i < row - 1; i++) {
            heap.offer(new Info(i, 0, heightMap[i][0]));
            heap.offer(new Info(i, col - 1, heightMap[i][col - 1]));
            visited[i][0] = true;
            visited[i][col - 1] = true;
        }
        int deep = 0;// 下限
        int ans = 0;
        while (!heap.isEmpty()) {
            Info info = heap.poll();
            int r = info.row;
            int c = info.col;
            // 更新下限
            deep = Math.max(info.value, deep);
            //把这个点的上下左右，计算结果，并放入小根堆
            if (r != 0 && !visited[r - 1][c]) {//上
                ans += Math.max(0, deep - heightMap[r - 1][c]);
                visited[r - 1][c] = true;
                heap.offer(new Info(r - 1, c, heightMap[r - 1][c]));
            }
            if (r != row - 1 && !visited[r + 1][c]) {//下
                ans += Math.max(0, deep - heightMap[r + 1][c]);
                visited[r + 1][c] = true;
                heap.offer(new Info(r + 1, c, heightMap[r + 1][c]));
            }
            if (c != 0 && !visited[r][c - 1]) {//左
                ans += Math.max(0, deep - heightMap[r][c - 1]);
                visited[r][c - 1] = true;
                heap.offer(new Info(r, c - 1, heightMap[r][c - 1]));
            }
            if (c != col - 1 && !visited[r][c + 1]) {//右
                ans += Math.max(0, deep - heightMap[r][c + 1]);
                visited[r][c + 1] = true;
                heap.offer(new Info(r, c + 1, heightMap[r][c + 1]));
            }
        }
        return ans;
    }

    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }

    }

    public static int trapRainWater2(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (int col = 0; col < M - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < N - 1; row++) {
            isEnter[row][M - 1] = true;
            heap.add(new Node(heightMap[row][M - 1], row, M - 1));
        }
        for (int col = M - 1; col > 0; col--) {
            isEnter[N - 1][col] = true;
            heap.add(new Node(heightMap[N - 1][col], N - 1, col));
        }
        for (int row = N - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }
        int water = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            if (r > 0 && !isEnter[r - 1][c]) {
                water += Math.max(0, max - heightMap[r - 1][c]);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }
            if (r < N - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < M - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }
}
