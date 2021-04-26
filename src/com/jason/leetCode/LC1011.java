package com.jason.leetCode;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 示例 2：
 *
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 *
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *  
 *
 * 提示：
 *
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1011 {
    public static void main(String[] args) {
//        int[] weights = {3,2,2,4,1,4};
//        int D = 3;
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int D = 5;
        int i = Solution.shipWithinDays(weights, D);
        System.out.println(i);
    }

    public static class Solution {
        public static int shipWithinDays(int[] weights, int D) {
            //1.一定存在一个最小的X使得刚好能在D天之内运完所有货物，小于该数的时候无法在D天运完，大于D的时候都可以运完
            //2.边界：左边界：数组中最大的一个元素，右边界，数字钟所有元素的和
            //二分法：取左右边界的中间值，中间值为能运完则取左边界和中间值继续二分，中间值无法运完则取中间值和右边界继续二分
            //知道左边界==右边界返回这个数字，就是最小的容量
            int max = Arrays.stream(weights).sum();
            int min = Arrays.stream(weights).max().getAsInt();
            System.out.println(max+"_"+min);
            int result = find(weights, D, min, max);
            return result;
        }

        private static int find(int[] weights, int d, int min, int max) {
            int middle = (min + max) / 2;
            if (max <= min) {
                return min;
            } else if (canFinish(weights, d, middle)) {
                return find(weights, d, min, middle);
            } else {
                return find(weights, d, middle+1, max);
            }
        }

        private static boolean canFinish(int[] weights, int d, int weightCount) {
            int pointer = 0;
            int length = weights.length;
            retry:
            while (d-- > 0){//d天都走完了还没装完，直接返回false
                int onDayWeight = weightCount;
                for (;;){
                    if (pointer >= length) {//全都取完了
                        return true;
                    }
                    if (onDayWeight >= weights[pointer]) {
                        onDayWeight -= weights[pointer++];
                    } else {
                        continue retry;
                    }
                }
            }
            return false;//走到这里说明n天都过了还没放完
        }

    }
}
