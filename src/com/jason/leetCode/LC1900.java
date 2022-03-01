package com.jason.leetCode;

import java.util.*;

/**
 * n 名运动员参与一场锦标赛，所有运动员站成一排，并根据 最开始的 站位从 1 到 n 编号（运动员 1 是这一排中的第一个运动员，运动员 2 是第二个运动员，依此类推）。
 *
 * 锦标赛由多个回合组成（从回合 1 开始）。每一回合中，这一排从前往后数的第 i 名运动员需要与从后往前数的第 i 名运动员比拼，获胜者将会进入下一回合。如果当前回合中运动员数目为奇数，那么中间那位运动员将轮空晋级下一回合。
 *
 * 例如，当前回合中，运动员 1, 2, 4, 6, 7 站成一排
 * 运动员 1 需要和运动员 7 比拼
 * 运动员 2 需要和运动员 6 比拼
 * 运动员 4 轮空晋级下一回合
 * 每回合结束后，获胜者将会基于最开始分配给他们的原始顺序（升序）重新排成一排。
 *
 * 编号为 firstPlayer 和 secondPlayer 的运动员是本场锦标赛中的最佳运动员。在他们开始比拼之前，完全可以战胜任何其他运动员。而任意两个其他运动员进行比拼时，其中任意一个都有获胜的可能，因此你可以 裁定 谁是这一回合的获胜者。
 *
 * 给你三个整数 n、firstPlayer 和 secondPlayer 。返回一个由两个值组成的整数数组，分别表示两位最佳运动员在本场锦标赛中比拼的 最早 回合数和 最晚 回合数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 11, firstPlayer = 2, secondPlayer = 4
 * 输出：[3,4]
 * 解释：
 * 一种能够产生最早回合数的情景是：
 * 回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * 回合 2：2, 3, 4, 5, 6, 11
 * 回合 3：2, 3, 4
 * 一种能够产生最晚回合数的情景是：
 * 回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * 回合 2：1, 2, 3, 4, 5, 6
 * 回合 3：1, 2, 4
 * 回合 4：2, 4
 * 示例 2：
 *
 * 输入：n = 5, firstPlayer = 1, secondPlayer = 5
 * 输出：[1,1]
 * 解释：两名最佳运动员 1 和 5 将会在回合 1 进行比拼。
 * 不存在使他们在其他回合进行比拼的可能。
 *  
 *
 * 提示：
 *
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-earliest-and-latest-rounds-where-players-compete
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author JasonC5
 */
public class LC1900 {

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int delegate = 0;
        for (int i = 1; i <= n; i++) {
            delegate |= 1 << i;
        }
        Map<Integer, int[]> cache = new HashMap<>();
        return process(delegate, firstPlayer, secondPlayer, 1, cache);
    }

    private int[] process(int delegate, int firstPlayer, int secondPlayer, int count, Map<Integer, int[]> cache) {
        if (cache.containsKey(delegate)) {
            int[] ints = cache.get(delegate);
            return new int[]{ints[0], ints[1]};
        }
        int min = Integer.MAX_VALUE, max = 0;
        int left = 0, right = 32;
        List<Integer> possible = new LinkedList<>();
        possible.add(0);
        while (left <= right) {
            while ((delegate & (1 << left)) == 0) {
                left++;
            }
            while ((delegate & (1 << right)) == 0) {
                right--;
            }
            if (left < right) {// 进行比赛
                if (left == firstPlayer && right == secondPlayer) {
                    return new int[]{count, count};
                } else if (left == firstPlayer || left == secondPlayer) {// left一定赢
                    possible = processQueue(possible, left);
                } else if (right == firstPlayer || right == secondPlayer) {// right一定赢
                    possible = processQueue(possible, right);
                } else {// 谁都可能赢
                    List<Integer> tmp = processQueue(possible, left);
                    tmp.addAll(processQueue(possible, right));
                    possible = tmp;
                }
                left++;
                right--;
            } else if (left == right) {// 自动胜出
                possible = processQueue(possible, left);
                break;
            }
        }
        // 这轮比赛完了，进行下一轮
        for (Integer next : possible) {
            int[] ans = process(next, firstPlayer, secondPlayer, count + 1, cache);
            min = Math.min(min, ans[0]);
            max = Math.max(max, ans[1]);
            cache.put(next, new int[]{min, max});
        }
        return new int[]{min, max};
    }

    private List<Integer> processQueue(List<Integer> possible, int offset) {
        List<Integer> list = new ArrayList<>();
        int mask = 1 << offset;
        for (Integer delegate : possible) {
            delegate |= mask;
            list.add(delegate);
        }
        return list;
    }

    public static void main(String[] args) {
        LC1900 lc1900 = new LC1900();
//        System.out.println(Arrays.toString(lc1900.earliestAndLatest(11, 2, 4)));
//        System.out.println(Arrays.toString(lc1900.earliestAndLatest(5, 1, 5)));
        System.out.println(Arrays.toString(lc1900.earliestAndLatest(5, 2, 3)));
    }
}
