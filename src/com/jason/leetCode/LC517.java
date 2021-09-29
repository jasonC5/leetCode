package com.jason.leetCode;

/**
 * 假设有 n台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * <p>
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * <p>
 * 给定一个整数数组machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-washing-machines
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC517 {
    public static int findMinMoves(int[] machines) {
        int sum = 0;
        int length = machines.length;
        for (int num : machines) {
            sum += num;
        }
        if (sum % length != 0) {
            return -1;
        }
        //到此必定有答案
        int avg = sum / length;
        int ans = 0;
        //【每次可选多个，每个可以选择往右给，或者往左给，每次分走1个，但是最多可能会接收2个    --左边一个，右边一个】
        // 如果把多个连续的数字看成一组，每次可以往左给出最多一个，从左要过来最多一个，往右同理
        int mastRightMove = 0;//从左往右，有多少个积压着往右移，【如果为负数，则是需要右边往左移】
        // 只把所有洗衣机分城两组，左边组合右边组，0~i  i+1~length-1 针对这两组来说，每次操作，只能往左或者往右给一件衣服 【或者同时给，但是和不变，没意义】
        for (int i = 0; i < length; i++) {
            int moveNum = machines[i] - avg;//这个点要转走多少件
            mastRightMove += moveNum;//整个左半边要往右边转走多少件
            // 对这个点要多少次操作，和此时整个左半边需要有多少操作取最大值，就是当前点的最小代价
            int max = Math.max(moveNum, Math.abs(mastRightMove));//这里moveNum不能是abs，负数的时候可以接收多个的，得按整体的处理
            // 所有点的最大最小代价，就是整排的最小代价
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {9, 1, 8, 8, 9};
        System.out.println(findMinMoves(num));
    }
}
