package com.jason.leetCode;

/**
 * 59. 螺旋矩阵 II
 */
public class LC59 {
    public static void main(String[] args) {
        int n = 3;
        int[][] ints = Solution_59.generateMatrix(n);
        System.out.println(ints);
    }
}
enum Direction_59{
    right,down,left,top
}
class Solution_59 {
    public static int[][] generateMatrix(int n) {
        int[][] data = new int[n][n];//返回值
        boolean[][] flag = new boolean[n][n];//标记是否已写
        int count = 0;//初始位置为向右
        int x = 0,y=0;//从00开始
        for (int i = 1; i <= n * n; i++) {
            if (flag[y][x]) {//遍历完成
                return data;
            }
            data[y][x] = i;
            flag[y][x] = true;
            Direction_59 value = Direction_59.values()[count];//方向
            switch (value){
                case right:
                    if (x+1 >= n || flag[y][x+1] ) {
                        count = ++count % 4;
                        y++;
                    } else {
                        x++;
                    }
                    break ;
                case down:
                    if (y+1 >= n || flag[y+1][x]) {
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
        return data;
    }
}