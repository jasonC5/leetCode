package com.jason.leetCode;

/**
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 2
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 3
 * 输出：5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC371 {

    public static int getSum(int a, int b) {
        int ans = 0;
        int carry = 0;
        int mask = 0;
        for (int i = 0; i < 32; i++) {
            mask = 1 << i;
            int ai = a & mask;
            int bi = b & mask;
            ans |= ai ^ bi ^ carry;
            //三个1，或者两个1的时候，都进位
            carry = (ai & bi & carry) == mask || ((ai | bi | carry) == mask && (ai ^ bi ^ carry) == 0) ? (mask << 1) : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int times = 1000;
        int max = 1200;
        for (int i = 0; i < times; i++) {
            int a = getInt(max);
            int b = getInt(max);
            int sum = getSum(a, b);
            if (sum != (a + b)) {
                System.out.println("a=" + a + ",b=" + b + ",sum=" + sum);
                return;
            }
        }
    }

    private static int getInt(int max) {
        return (int) (Math.random() * max) + 1;
    }

}
