package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class LC54 {
    public static void main(String[] args) {

        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix = {{1}};
//        int[][] matrix = {{3},{2}};
        List<Integer> integers = Solution.spiralOrder(matrix);
        integers.forEach(System.out::println);
    }
}

enum Direction{
    right,down,left,top
}

class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        int columns = matrix[0].length;//列数
        int rows = matrix.length;//行数
        boolean[][] flag = new boolean[rows][columns];//标记是否读取
        int count = 0;//初始位置为向右
        int x = 0,y=0;//从00开始读
        for (int i = 0;i < columns  * rows;i++) {
            if (flag[y][x]) {//遍历完成
                return list;
            }
            list.add(matrix[y][x]);
            flag[y][x] = true;
            Direction value = Direction.values()[count];//方向
            switch (value){
                case right:
                    if (x+1 >= columns || flag[y][x+1] ) {
                        count = ++count % 4;
                        y++;
                    } else {
                        x++;
                    }
                    break ;
                case down:
                    if (y+1 >= rows || flag[y+1][x]) {
                        count = ++count % 4;
                        x--;
                    } else {
                        y++;
                    }
                    break ;
                case left:
                    if (x-1 < 0 || flag[y][x-1]) {
                        count = ++count % 4;
                        y--;
                    } else {
                        x--;
                    }
                    break ;
                case top:
                    if (y-1 < 0 || flag[y-1][x]) {
                        count = ++count % 4;
                        x++;
                    } else {
                        y--;
                    }
                    break ;
            }
        }
        return list;
    }

    public static List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        int left = 0,right=matrix[0].length-1,top=0,bottom=matrix.length-1;//控制窗口
        int count = 0;//初始位置为向右
        //按照右下左上顺序打印
        //每次到达边界（根据4个窗口边界判断）需要转向，
        //4个方法，往左读，往下读，往右读，往上读
        //每次读到尽头，count++.
        //方向：count % 4
//            list.add();
        Boolean dealSpecial = false;
        retry:
        for (;;){
            Direction value = Direction.values()[count];
            switch (value){
                case right:
                    list.addAll(readByRight(matrix,left,right,top,bottom));
                    top++;
                    break ;
                case down:
                    list.addAll(readByDown(matrix,left,right,top,bottom));
                    right--;
                    break ;
                case left:
                    list.addAll(readByLeft(matrix,left,right,top,bottom));
                    bottom--;
                    break ;
                case top:
                    list.addAll(readByUp(matrix,left,right,top,bottom));
                    left++;
                    break ;
            }
            if (top >= bottom && left >= right) {
                if (matrix.length == matrix[0].length && matrix.length!=1 && !dealSpecial){
                    dealSpecial = true;//已执行特殊点【对称情况下的中心点】
                    continue retry;
                } else {
                    break retry;
                }
            }
            count = ++count % 4;
        }
        return list;
    }

    private static List<Integer> readByUp(int[][] matrix, int left, int right, int top, int bottom) {
        List<Integer> array = new LinkedList<>();
        for (int i = bottom;i>=top;i--){
            array.add(matrix[i][left]);
        }
        return array;
    }

    private static List<Integer> readByDown(int[][] matrix, int left, int right, int top, int bottom) {
        List<Integer> array = new LinkedList<>();
        for (int i = top;i<=bottom;i++){
            array.add(matrix[i][right]);
        }
        return array;
    }

    private static List<Integer> readByRight(int[][] matrix, int left, int right, int top, int bottom) {
        int[] tmp = matrix[top];
        List<Integer> array = new LinkedList<>();
        for (int i = left;i<=right;i++){
            array.add(tmp[i]);
        }
        return array;
    }

    private static List<Integer> readByLeft(int[][] matrix, int left, int right, int top, int bottom) {
        int[] tmp = matrix[bottom];
        List<Integer> array = new LinkedList<>();
        for (int i = right;i>=left;i--){
            array.add(tmp[i]);
        }
        return array;
    }

}