package com.jason.leetCode;

/**
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 * <p>
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *  
 * <p>
 * 提示：
 * <p>
 * num1 和 num2 都是有效的复数表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC537 {
    public static String complexNumberMultiply(String num1, String num2) {
        int[] x1 = split(num1);
        int[] x2 = split(num2);
        int ans1 = x1[0] * x2[0] - x1[1] * x2[1];
        int ans2 = x1[0] * x2[1] + x1[1] * x2[0];
        return ans1 + "+" + ans2 + "i";
    }

    private static int[] split(String num1) {
        int[] ints = new int[2];
        String[] split = num1.split("\\+");
        ints[0] = Integer.parseInt(split[0]);
        String[] is = split[1].split("i");
        ints[1] = Integer.parseInt(is[0]);
        return ints;
    }

    public static void main(String[] args) {
        String num1 = "1+-1i", num2 = "1+-1i";
        System.out.println(complexNumberMultiply(num1, num2));
    }
}
