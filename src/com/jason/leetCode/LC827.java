package com.jason.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * <p>
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * <p>
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 * <p>
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 * <p>
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/making-a-large-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/9/29 16:47:47
 * @description
 */
public class LC827 {
    public static final int[] POSITION = {1, 0, -1, 0, 1};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        // 构建并查集
        UnionFind uf = new UnionFind();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    uf.find(i * n + j);
                    if (i < n - 1 && grid[i + 1][j] == 1) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                    ans = Math.max(ans, uf.count(i * n + j));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int cur = 1;
                    // 上下左右
                    Set<Integer> visited = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        if (i + POSITION[k] < n && i + POSITION[k] >= 0 && j + POSITION[k + 1] < n && j + POSITION[k + 1] >= 0 && grid[i + POSITION[k]][j + POSITION[k + 1]] == 1) {
                            int parent = uf.find((i + POSITION[k]) * n + j + POSITION[k + 1]);
                            if (visited.add(parent)){
                                cur += uf.count((i + POSITION[k]) * n + j + POSITION[k + 1]);
                            }
                        }
                    }
                    ans = Math.max(ans, cur);
                }
            }
        }
        return ans;
    }

    private class UnionFind {

        // 每个节点的根节点
        private Map<Integer, Integer> parent;
        // 一共有几块
//        private int count;
        // 该节点所属的块，一共有多少个节点
        private Map<Integer, Integer> countMap;

        public UnionFind() {
            this.parent = new HashMap<>();
//            this.count = 0;
            this.countMap = new HashMap<>();
        }

        public int count(int x) {
            if (x != parent.get(x)) {//如果不是指向自己（不是新节点），递归找到根
                // 懒更新
                parent.put(x, find(parent.get(x)));//
            }
            return countMap.get(parent.get(x));
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {//找不到这个点的根，添加一个节点
                parent.put(x, x);//0->0
                // 新的块，只有1个点
                countMap.put(x, 1);
            }
            // 懒更新
            if (x != parent.get(x)) {//如果不是指向自己（不是新节点），递归找到根
                parent.put(x, find(parent.get(x)));//
            }
            return parent.get(x);//返回根
        }

        public void union(int x, int y) {// 10001 0   10002 0
            int rootX = find(x);//10001 count++  10002  count++     10001       0
            int rootY = find(y);//0     count++  1      count++     1           1
            if (rootX == rootY) {    // count--         count--             count--
                return;
            }

            parent.put(rootX, rootY);//10001->1  0->1   10002->1  1->1
            // 两个连通分量合并成为一个
            countMap.put(rootY, countMap.get(rootY) + countMap.get(rootX));
            countMap.remove(rootX);
        }
    }
}
