package com.jason.leetCode;

/**
 * @author Administrator
 */
public class LC427 {

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public static Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, 0, 0, n);
    }

    private static Node build(int[][] grid, int row, int col, int width) {
        Node node = new Node();
        int val = grid[row][col];
        boolean isLeaf = true;
        retry:
        for (int i = row; i < row + width; i++) {
            for (int j = col; j < col + width; j++) {
                if (val != grid[i][j]) {
                    isLeaf = false;
                    break retry;
                }
            }
        }
        if (isLeaf) {
            node.isLeaf = true;
            node.val = val == 1;
        } else {
            int nextWidth = width >> 1;
            node.topLeft = build(grid, row, col, nextWidth);
            node.topRight = build(grid, row, col + nextWidth, nextWidth);
            node.bottomLeft = build(grid, row + nextWidth, col, nextWidth);
            node.bottomRight = build(grid, row + nextWidth, col + nextWidth, nextWidth);
        }
        return node;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};
        System.out.println(construct(grid));
    }
}
