package com.jason.leetCode;

public class LC803 {

    public static void main(String[] args) {
        int[][] grid = {{1},{1},{1},{1},{1}};
        int[][] hits = {{3,0},{4,0},{1,0},{2,0},{0,0}};
        System.out.println(hitBricks(grid, hits));
    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {//深度优先思路【超时了】
        //深度优先
        //广度优先
        //并查集

        //稳定的条件，
        //1.直接与顶部相连
        //2.能到达顶部

        //深度优先：int[x][y] = ? 若 = 0 则一个都不消除
        //若  int[x][y] = 1 则 改为0，同时查看上下左右四个位置，若最终能连到上边界或者左右边界，



        //思路1：
        //当这个点存在的时候，计算有几个点能最终与顶部相连
        //当这个点去掉之后，有几个点能与顶部相连
        //做减法就能知道能消除几个点

        //深度优先，遍历第0行，为1的时候置为0，将上下左右所有的位置都改为0，并递归此动作
        //再遍历该图，剩下的为1的，都是没有连接到顶端的，也就是这次除了指定位置之外被清除的点


        int[] result = new int[hits.length];
        int row = grid.length;
        int column = grid[0].length;
        for(int k=0;k<hits.length;k++){
            int count = 0;
            int[] point = hits[k];
            int x = point[0];
            int y = point[1];
            if(grid[x][y] == 0){//
                result[k] = count;
                continue;
            } else {
                grid[x][y] = 0;//将
            }
            //四个状态：-1翻转的砖块 1砖块 0空白
            //遍历三遍，或者添加一个容器记录
            // int[][] tmp = new int[row][column];
            //所有和顶部相连的砖块翻转，再遍历列表，没有翻转的，就是掉落的砖块（要不要记录？还是直接填空白）
            // for(int i = 0;i<row; i++){
            for(int j=0 ; j<column;j++){
                int value = grid[0][j];
                if(value == 1){//砖块
                    flib(grid, 0, j);
                } else {
                    //为0或者-1（已经被翻转）不用管
                }
            }
            // }
            //翻转完毕，剩下的状态：-1翻转的砖块 0空白 1本次掉落的砖块
            for(int i=0 ; i<row;i++){
                for(int j=0 ; j<column;j++){
                    if(grid[i][j]==1){
                        grid[i][j] = 0;
                        count++;
                    } else {//翻转回来
                        grid[i][j] = 0-grid[i][j];
                    }
                }
            }
            result[k] = count;//遍历完毕，
        }
        return result;

    }

    public static void flib(int[][] grid, int x, int y){
        //自己翻转，然后上下左右都翻转，什么时候上下左右都 ！=0 什么时候return
        int row = grid.length;
        int column = grid[0].length;
        grid[x][y] = 0-grid[x][y];
        //上下左右递归
        if(x > 0 && (grid[x-1][y] == 1)){
            flib(grid, x-1, y);
        }
        if((x+1) < row && grid[x+1][y] == 1){
            flib(grid, x+1, y);
        }
        if(y>0 && grid[x][y-1] == 1){
            flib(grid, x, y-1);
        }
        if((y+1) < column && grid[x][y+1] == 1){
            flib(grid, x, y+1);
        }
    }
}
