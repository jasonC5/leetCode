package com.jason.leetCode;

/**
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC240 {
//    public static boolean searchMatrix(int[][] matrix, int target) {
//        int rowLen = matrix.length;
//        int colLen = matrix[0].length;
//        if (target < matrix[0][0] || target > matrix[rowLen - 1][colLen - 1]) {
//            return false;
//        }
//        int col = 0;
//        for (; col < matrix[0].length; col++) {
//            if (target == matrix[0][col]) {
//                return true;
//            } else if (col == matrix[0].length - 1 || target < matrix[0][col + 1]) {
//                break;
//            }
//        }
//        for (int[] ints : matrix) {
//            if (target == ints[col]) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[rowLen - 1][colLen - 1]) {
            return false;
        }
        boolean[][] visited = new boolean[rowLen][colLen];
        return process(matrix, target, rowLen, colLen, 0, 0, visited);
    }

    private static boolean process(int[][] matrix, int target, int rowLen, int colLen, int i, int j, boolean[][] visited) {
        //越界或者已访问
        if (i < 0 || i >= rowLen || j < 0 || j >= colLen || visited[i][j]) {
            return false;
        }
        // 标记
        visited[i][j] = true;
        // 跳
        if (matrix[i][j] == target) {
            return true;
        } else if (matrix[i][j] < target) {
            return process(matrix, target, rowLen, colLen, i + 1, j, visited) || process(matrix, target, rowLen, colLen, i, j + 1, visited);
        } else {
            return process(matrix, target, rowLen, colLen, i - 1, j, visited) || process(matrix, target, rowLen, colLen, i, j - 1, visited);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 22},
                {21, 22, 23, 24, 25}};
        int target = 20;
        System.out.println(searchMatrix2(matrix, target));
    }

    //每一行二分
    private static boolean searchMatrix2(int[][] matrix, int target) {
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }
        for (int[] arr : matrix) {
            int left = 0, right = arr.length - 1;
            int mid;
            while (left <= right) {
                mid = left + ((right - left) >> 1);
                if (arr[mid] < target) {
                    left = mid + 1;
                } else if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
