package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/19 9:41:27
 * @description
 */
public class LC1971 {

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        public void union(int a, int b) {
            parent[find(b)] = parent[find(a)];
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.find(source) == unionFind.find(destination);
    }
}
