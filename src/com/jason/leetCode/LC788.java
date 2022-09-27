package com.jason.leetCode;

/**
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 * <p>
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * <p>
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rotated-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenjieaj
 * @date 2022/9/26 9:15:13
 * @description
 */
public class LC788 {
    // 旋转后是自己：0,1,8
    // 旋转后不是自己：2,5,6,9
    public int rotatedDigits(int n) {
        // 每位都在(2, 5, 6, 9, 0, 1, 8)内，至少一位在(2, 5, 6, 9)内
        int ans = 0;
        for (int i = 1; i <= n; i++) {//计算从 1 到 N 中有多少个数 X 是好数？
            int cntMirror = 0; //计算2, 5, 6, 9的个数
            int cntValid = 0;//计算2, 5, 6, 9, 0, 1, 8的个数
            String handle = String.valueOf(i);
            for (char c : handle.toCharArray()) {
                switch (c) {
                    case '2':
                    case '5':
                    case '6':
                    case '9':
                        cntMirror++;
                        cntValid++;
                        break;
                    case '0':
                    case '1':
                    case '8':
                        cntValid++;
                        break;
                    default:
                        break;
                }
            }
            if (cntMirror > 0 && cntValid == handle.length()) {
                ans++;
            }
        }
        return ans;
    }
}
