package com.jason.leetCode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数2、3 和/或5的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1690
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC264 {

    public static void main(String[] args) {
        final int number = Solution.nthUglyNumber(10);
        System.out.println(number);
    }

    public static class Solution {
        //动态规划解法
        public static int nthUglyNumber(int n) {
            //丑数：val = 2^x*3^y*5^z(x[0,+∞],y[0,+∞],z[0,+∞])
            //val[1] = 1 （此时x=y=0）
            int val[] = new int[n];
            val[0] = 1;
            //定义三个指针，*2，*3，*5
            int x=0,y=0,z=0;
            for (int i = 1; i < n; i++) {//O(N)
                int vx = val[x]*2,vy=val[y]*3,vz=val[z]*5;
                val[i] = Math.min(vx,Math.min(vy,vz));//三个中的最小数为下一个，并且指针往前挪一位
                if (val[i] == vx) {
                    x++;
                }
                if (val[i] == vy) {
                    y++;
                }
                if (val[i] == vz) {
                    z++;
                }
            }
            return val[n-1];
        }

        //小顶堆解法
        public static int nthUglyNumber2(int n) {
            int[] factors = {2, 3, 5};
            Set<Long> seen = new HashSet<Long>();//去重
            PriorityQueue<Long> heap = new PriorityQueue<Long>();//堆
            seen.add(1L);
            heap.offer(1L);
            int ugly = 0;
            //整体思路 每次都把第K个数依次*235，都放入堆中【去重】，循环N次，最终得到一个小顶堆
            // 【堆中数字会多于N，小于等于3N，且前面N个能保证是实际丑数顺序的，N之后的可能中间会漏掉，需要继续遍历】
            //时间复杂度和空间复杂度都高 O(N*logN)
            for (int i = 0; i < n; i++) {
                long curr = heap.poll();
                ugly = (int) curr;
                for (int factor : factors) {
                    long next = curr * factor;
                    if (seen.add(next)) {//去重
                        heap.offer(next);
                    }
                }
            }
            return ugly;
        }
    }
}
