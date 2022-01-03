package com.jason.leetCode;

/**
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * <p>
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/elimination-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC390 {
    public static int lastRemaining(int n) {
        // 1.第一行，一定消除的是所有的奇数
        // 2.第二行：从右往左消除，全体/2 ，是 1~n/2,剩下的：1~n/2-1 (n/2 是偶数)  或者 2~n/2-1(n/2是奇数)
        // 3.第三行：从左往右消除，消掉所有奇数位

        //1.每次消除，整体长度 len` = len/2
        //2.只需要记录下来每次消除第一个数字和最后一个数字，因为最终只有一个的时候要么是上次的第一个，要么是上次的最后一个
        //3.每次消除完，最后剩下的，都是等差数列
        int first = 1,  grade = 1, len = n, stage = 0;
        while (len != 1) {
            if ((stage & 1) == 0) {//偶数步从左往右
                first += grade;
            } else {// 奇数步，从右往左
                first += (len & 1) == 0 ? 0 : grade;
            }
            stage++;//步数
            len >>= 1;// 数组长度
            grade <<= 1;// 等差
        }
        return first;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(6));
    }

    public int lastRemaining2(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }
}
