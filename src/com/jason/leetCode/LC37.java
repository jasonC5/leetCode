package com.jason.leetCode;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：board = [['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.','.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']]
 * 输出：[['5','3','4','6','7','8','9','1','2'],['6','7','2','1','9','5','3','4','8'],['1','9','8','3','4','2','5','6','7'],['8','5','9','7','6','1','4','2','3'],['4','2','6','8','5','3','7','9','1'],['7','1','3','9','2','4','8','5','6'],['9','6','1','5','3','7','2','8','4'],['2','8','7','4','1','9','6','3','5'],['3','4','5','2','8','6','1','7','9']]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC37 {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);
        System.out.println(board);
    }


    public static void solveSudoku(char[][] board) {
        boolean[][] rowExist = new boolean[9][10];
        boolean[][] colExist = new boolean[9][10];
        boolean[][] bucketExist = new boolean[9][10];
        handleExist(board, rowExist, colExist, bucketExist);
        process(board, 0, 0, rowExist, colExist, bucketExist);
    }

    private static Boolean process(char[][] board, int row, int col, boolean[][] rowExist, boolean[][] colExist, boolean[][] bucketExist) {
        if (row == 9) {
            return true;
        }
        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = col == 8 ? 0 : col + 1;
        if ('.' == board[row][col]) {
            int bucket = 3 * (row / 3) + (col / 3);
            //0~9中过滤出来可以填的数字
            for (int num = 1; num <= 9; num++) {
                if (rowExist[row][num] || colExist[col][num] || bucketExist[bucket][num]) {
                    continue;
                }
                rowExist[row][num] = true;
                colExist[col][num] = true;
                bucketExist[bucket][num] = true;
                board[row][col] = (char) (num + '0');
                if (process(board, nextRow, nextCol, rowExist, colExist, bucketExist)) {
                    return true;
                }
                //恢复现场
                rowExist[row][num] = false;
                colExist[col][num] = false;
                bucketExist[bucket][num] = false;
                board[row][col] = '.';
            }
            return false;
        } else {
            return process(board, nextRow, nextCol, rowExist, colExist, bucketExist);
        }
    }

    private static void handleExist(char[][] board, boolean[][] rowExist, boolean[][] colExist, boolean[][] bucketExist) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if ('.' != board[row][col]) {
                    int bucket = 3 * (row / 3) + (col / 3);
                    int num = board[row][col] - '0';
                    rowExist[row][num] = true;
                    colExist[col][num] = true;
                    bucketExist[bucket][num] = true;
                }
            }
        }
    }

}
