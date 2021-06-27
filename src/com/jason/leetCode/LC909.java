package com.jason.leetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * N x N 的棋盘board 上，按从1 到 N*N的数字给方格编号，编号 从左下角开始，每一行交替方向。
 * <p>
 * 例如，一块 6 x 6 大小的棋盘，编号如下：
 * <p>
 * <p>
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board{r}{c} != -1，那个蛇或梯子的目的地将会是 board{r}{c}。
 * <p>
 * 玩家从棋盘上的方格1 （总是在最后一行、第一列）开始出发。
 * <p>
 * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
 * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 {x+1, x+6} 之间。
 * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
 * <p>
 * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：{
 * {-1,-1,-1,-1,-1,-1},
 * {-1,-1,-1,-1,-1,-1},
 * {-1,-1,-1,-1,-1,-1},
 * {-1,35,-1,-1,13,-1},
 * {-1,-1,-1,-1,-1,-1},
 * {-1,15,-1,-1,-1,-1}}
 * 输出：4
 * 解释：
 * 首先，从方格 1 {第 5 行，第 0 列} 开始。
 * 你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
 * 然后你决定移动到方格 17 {第 3 行，第 5 列}，必须爬过蛇到方格 13。
 * 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
 * 然后你决定移动到方格 36, 游戏结束。
 * 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= board.length = board{0}.length<= 20
 * board{i}{j}介于1和N*N之间或者等于-1。
 * 编号为1的方格上没有蛇或梯子。
 * 编号为N*N的方格上没有蛇或梯子。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/snakes-and-ladders
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC909 {
    static int[][] BOARD;

    public static void main(String[] args) {
//        int[][] board = {
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 35, -1, -1, 13, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 15, -1, -1, -1, -1}
//        };
//        int[][] board = {
//                {-1, -1, -1},
//                {-1, 9, 8},
//                {-1, 8, 9}};
        int[][] board = {
                {-1, 1, 2, -1},
                {2, 13, 15, -1},
                {-1, 10, -1, -1},
                {-1, 6, 2, 8}};
        BOARD = board;
        System.out.println(snakesAndLadders(board));
    }

    public static int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        //广度优先遍历
        //问题点1：下标和棋盘相互映射
        //问题点2：起始点、终点、宽度优先遍历，哈希表记录历史
        int row = board.length;
        int col = board[0].length;
        int ans = row * col;
        int step = 0;
        Queue<Integer> q = new LinkedList();
        HashSet<Integer> seen = new HashSet<>();
        q.add(1);
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            for (int i = 0; i < size; i++) {
                Integer idx = q.poll();
                if (idx + 6 >= ans) {
                    return step;
                }
                //后面6个数
                for (int j = 1; j <= 6; j++) {
                    //两个功能：1.idx转成二维数下标，2.查看是否是-1，如果是-1直接返回，若不是则需发生跳转，返回跳转的数字
                    int next = find(idx + j);
                    //直接跳到终点了
                    if (next == ans) {
                        return step;
                    }
                    if (seen.contains(next)) {
                        continue;
                    }
                    seen.add(next);
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    private static int find(int id) {
        int n = BOARD.length;
        //转成二尾数组下标【棋盘下标】，并查看是否是-1，若是直接返回i，若不是则返回上面的数字
        int r = (id - 1) / n, c = (id - 1) % n;
        //奇数和偶数行的顺序是反的
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return BOARD[n - 1 - r][c] == -1 ? id : BOARD[n - 1 - r][c];
    }

}
