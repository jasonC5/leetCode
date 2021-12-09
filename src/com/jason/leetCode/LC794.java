package com.jason.leetCode;

/**
 * 用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
 * <p>
 * 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。
 * “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 示例 1:
 * 输入: board = ["O  ", "   ", "   "]
 * 输出: false
 * 解释: 第一个玩家总是放置“X”。
 * <p>
 * 示例 2:
 * 输入: board = ["XOX", " X ", "   "]
 * 输出: false
 * 解释: 玩家应该是轮流放置的。
 * <p>
 * 示例 3:
 * 输入: board = ["XXX", "   ", "OOO"]
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: board = ["XOX", "O O", "XOX"]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC794 {
    public boolean validTicTacToe(String[] board) {
        // 1.numX == numO || numX == numO + 1
        // 2.if(X win)  numX == numO + 1
        // 3.if(O win)  numX == numO
        char[][] charBoard = buildBoard(board);
        int xNum = getNum(charBoard, 'X');
        int oNum = getNum(charBoard, 'O');
        if (win(charBoard, 'X')) {
            return !win(charBoard, 'O') && xNum == oNum + 1;
        } else if (win(charBoard, 'O')) {
            return xNum == oNum;
        } else {
            return xNum == oNum + 1 || xNum == oNum;
        }
    }

    private boolean win(char[][] charBoard, char c) {
        for (int i = 0; i < 3; i++) {
            // 第i行
            if (charBoard[i][0] == c && charBoard[i][1] == c && charBoard[i][2] == c) {
                return true;
            }
            // 第i列
            if (charBoard[0][i] == c && charBoard[1][i] == c && charBoard[2][i] == c) {
                return true;
            }
        }
        // 两条对角线
        if (charBoard[0][0] == c && charBoard[1][1] == c && charBoard[2][2] == c) {
            return true;
        }
        if (charBoard[0][2] == c && charBoard[1][1] == c && charBoard[2][0] == c) {
            return true;
        }
        return false;
    }

    private int getNum(char[][] charBoard, char c) {
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                num += c == charBoard[i][j] ? 1 : 0;
            }
        }
        return num;
    }

    private char[][] buildBoard(String[] board) {
        char[][] charBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            charBoard[i] = board[i].toCharArray();
        }
        return charBoard;
    }

    public static void main(String[] args) {
        LC794 test = new LC794();
        String[] board = {"OOO","XXO","XXX"};
        System.out.println(test.validTicTacToe(board));
    }
}
