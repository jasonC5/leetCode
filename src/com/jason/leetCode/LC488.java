package com.jason.leetCode;

import java.util.Arrays;

/**
 * 你正在参与祖玛游戏的一个变种。
 * <p>
 * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
 * <p>
 * 你的目标是 清空 桌面上所有的球。每一回合：
 * <p>
 * 从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
 * 接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
 * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
 * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
 * 重复这个过程，直到你赢了游戏或者手中没有更多的球。
 * 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuma-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC488 {
    public int findMinStep(String board, String hand) {
        char[] boards = board.toCharArray();
        char[] hands = hand.toCharArray();
        int ans = process(boards, hands, 0, 0);
        return ans;
    }

    private int process(char[] boards, char[] hands, int idxB, int idxH) {
        if (boards.length == 0) {
            return 0;
        } else if (idxH == hands.length) {
            return -1;
        }
        char[] newBoards = new char[boards.length + 1];
        // TODO
        return 0;
    }


    public char[] clean(char[] boards, int idx) {
        int left = idx, right = idx;
        while (true) {
            if (left == 0 && right == boards.length - 1) {// 消完了
                break;
            } else if (left > 0 && boards[left] == boards[left - 1]) {// 往左边扩
                left--;
            } else if (right < boards.length - 1 && boards[right] == boards[right + 1]) {//往右边阔
                right++;
            }
        }
        if (right - left > 1) {
            //超过3个，消掉中间的
            char[] newArr = new char[boards.length - (right - left + 1)];
            for (int i = 0; i < left; i++) {
                newArr[i] = boards[i];
            }
            for (int i = left; i < newArr.length; i++) {
                newArr[i] = boards[i + right - left + 1];
            }
            // 消掉之后，如果两两边的一样，继续递归调用
            if (left > 0 && right < boards.length - 1 && boards[left - 1] == boards[right + 1]) {
                return clean(newArr, left - 1);
            }
            return newArr;
        }
        // 消不掉，直接返回
        return boards;
    }
}
