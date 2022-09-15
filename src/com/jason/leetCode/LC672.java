package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 房间中有 n 只已经打开的灯泡，编号从 1 到 n 。墙上挂着 4 个开关 。
 * <p>
 * 这 4 个开关各自都具有不同的功能，其中：
 * <p>
 * 开关 1 ：反转当前所有灯的状态（即开变为关，关变为开）
 * 开关 2 ：反转编号为偶数的灯的状态（即 2, 4, ...）
 * 开关 3 ：反转编号为奇数的灯的状态（即 1, 3, ...）
 * 开关 4 ：反转编号为 j = 3k + 1 的灯的状态，其中 k = 0, 1, 2, ...（即 1, 4, 7, 10, ...）
 * 你必须 恰好 按压开关 presses 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。
 * <p>
 * 给你两个整数 n 和 presses ，执行完所有按压之后，返回 不同可能状态 的数量。
 * <p>
 * 分为6组：,能影响的开关
 * 6n       1,2
 * 6n+1     1,3,4
 * 6n+2     1,2--
 * 6n+3     1,3
 * 6n+4     1,2,4
 * 6n+5     1,3--
 * 分为4组，4个开关
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bulb-switcher-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/9/15 9:06:50
 * @description
 */
public class LC672 {
    /**
     * @param n       一共几盏灯
     * @param presses 一共按几次开关
     * @return
     */
    public int flipLights(int n, int presses) {
        Set<Integer> seen = new HashSet<Integer>();
        // 4个开关最终状态全枚举
        for (int i = 0; i < 1 << 4; i++) {
            // 每个开关，是开还是关
            int[] pressArr = new int[4];
            for (int j = 0; j < 4; j++) {
                pressArr[j] = (i >> j) & 1;
            }
            // 开关按下的最小次数（去掉开了又关的次数）
            int sum = Arrays.stream(pressArr).sum();
            // 保证指定次数能按到当前状态
            if ((sum & 1) == (presses & 1) && sum <= presses) {
                //每个二进制位代表一种灯的状态
                int delegate = pressArr[0] ^ pressArr[1];
                if (n > 1) {
                    delegate |= (pressArr[0] ^ pressArr[2] ^ pressArr[3]) << 1;
                }
                if (n > 2) {
                    delegate |= (pressArr[0] ^ pressArr[2]) << 2;
                }
                if (n > 3) {
                    delegate |= (pressArr[0] ^ pressArr[1] ^ pressArr[3]) << 3;
                }
                seen.add(delegate);
            }
        }
        return seen.size();
    }
}
