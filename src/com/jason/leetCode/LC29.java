package com.jason.leetCode;

/**
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数dividend除以除数divisor得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC29 {

    // 暴力解
    public static int divide0(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        } else if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        // 符号位
        int symbol = ((dividend >>> 31) ^ (divisor >>> 31));
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        int ans = 0;
        while (dividend <= divisor) {
            dividend -= divisor;
            ans++;
            if (ans == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }
        return symbol == 0 ? ans : -ans;
    }

    public static void main(String[] args) {
//        int dividend = -2147483648, divisor = -1;
        int times = 1_000_000;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int dividend = random(), divisor = random();
            int ans1 = divide0(dividend, divisor);
            int ans2 = divide(dividend, divisor);
            if (ans1 != ans2) {
                System.out.println("error");
                System.out.println("dividend=" + dividend + ", divisor=" + divisor + ", ans1=" + ans1 + ", ans2=" + ans2);
                return;
            }
        }
        System.out.println("finished");
    }

    private static int random() {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans |= (Math.random() > 0.5 ? 1 : 0) << i;
        }
        return ans;
    }


    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        // 当除数为1，直接返回被除数
        if (divisor == 1) {
            return dividend;
        }
        // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        //符号位
        int symbol = ((dividend >>> 31) ^ (divisor >>> 31)) == 0 ? 1 : -1;
        // 转为long再运算
        return symbol * divide(Math.abs((long) dividend), Math.abs((long) divisor));
    }

    private static int divide(long dividend, long divisor) {// dividend > 0  divisor > 0
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor; // 记录用了count个divisor的和
        int count = 1; // 使用了多少个divisor
        while (dividend >= sum) {//每次*2
            sum <<= 1;
            count <<= 1;
        }
        // 回退一步
        sum >>>= 1;
        count >>>= 1;
        // 此时dividend >= sum
        // 将count个divisor从dividend消耗掉，剩下的还需要多少个divisor交由递归函数处理
        return count + divide(dividend - sum, divisor);
    }

}
