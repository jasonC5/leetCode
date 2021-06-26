package com.jason.leetCode;

import java.util.*;

/**
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用0来表示.
 * <p>
 * 一次移动定义为选择0与一个相邻的数字（上下左右）进行交换.
 * <p>
 * 最终当板board的结果是[[1,2,3],[4,5,0]]谜板被解开。
 * <p>
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * <p>
 * 示例：
 * <p>
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC773 {
    public static int slidingPuzzle(int[][] board) {
        //宽度优先遍历+Queue
        String ans = "123450";
        String now = arrToString(board);
        int step = 0;
        if (now.equals(ans)) {
            return 0;
        }
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(now);
        seen.add(now);
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            for (int i = 0; i < size; i++) {
                List<String> possibleStr = possible(q.poll());
                for (String possible : possibleStr) {
                    if (!seen.contains(possible)) {
                        if (possible.equals(ans)) {
                            return step;
                        }
                        seen.add(possible);
                        q.offer(possible);
                    }
                }
            }
        }
        return -1;
    }

    private static List<String> possible(String str) {
        List<String> ans = new ArrayList<>();
        char[] chars = str.toCharArray();
        //就6个
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                //上下左右交换
                //下
                int swap;
                if ((swap = i + 3) < 6) {
                    doSwap(chars, i , swap);
                    ans.add(new String(chars));
                    //加完再换回来
                    doSwap(chars, i , swap);
                }
                //上
                if ((swap = i - 3) >= 0) {
                    doSwap(chars, i , swap);
                    ans.add(new String(chars));
                    //加完再换回来
                    doSwap(chars, i , swap);
                }
                //左
                if ((swap = i - 1) >= 0 && swap != 2) {
                    doSwap(chars, i , swap);
                    ans.add(new String(chars));
                    //加完再换回来
                    doSwap(chars, i , swap);
                }
                //右
                if ((swap = i + 1) < 6 && swap != 3) {
                    doSwap(chars, i , swap);
                    ans.add(new String(chars));
                    //加完再换回来
                    doSwap(chars, i , swap);
                }
            }
        }
        return ans;
    }

    private static void doSwap(char[] chars, int i, int swap) {
        chars[i] ^= chars[swap];
        chars[swap] ^= chars[i];
        chars[i] ^= chars[swap];
    }

    private static String arrToString(int[][] board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                stringBuilder.append(anInt);
            }
        }
        return stringBuilder.toString();
    }



    //对数器
    static int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public static int slidingPuzzle2(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(board[i][j]);
            }
        }
        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(initial);
        Set<String> seen = new HashSet<String>();
        seen.add(initial);

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    if (!seen.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }

        return -1;
    }

    // 枚举 status 通过一次交换操作得到的状态
    public static List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        int x = status.indexOf('0');
        for (int y : neighbors[x]) {
            doSwap(array, x, y);
            ret.add(new String(array));
            doSwap(array, x, y);
        }
        return ret;
    }



    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 5, 0}};
        System.out.println(slidingPuzzle(board));
        System.out.println(slidingPuzzle2(board));
    }

}
