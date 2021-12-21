package com.jason.leetCode;

/**
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * <p>
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [['X','.','.','X'],['.','.','.','X'],['.','.','.','X']]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：board = [['.']]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/battleships-in-a-board
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC419 {
    public static int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    ans++;
                    flip(board, i, j);
                }
            }
        }
        return ans;
    }

    public static final int[] LOCATION = {1, 0, -1, 0, 1};

    private static void flip(char[][] board, int i, int j) {
        board[i][j] = '.';
        for (int k = 0; k < 4; k++) {
            int nextI = i + LOCATION[k];
            int nextJ = j + LOCATION[k + 1];
            if (nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length && board[nextI][nextJ] == 'X') {
                flip(board, nextI, nextJ);
            }
        }
    }

    public static void main(String[] args) {
//        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        char[][] board = {{'X', 'X', 'X'}};
        System.out.println(countBattleships(board));
    }
}
