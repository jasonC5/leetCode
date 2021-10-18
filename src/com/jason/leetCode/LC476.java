package com.jason.leetCode;

/**
 * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * 示例 2：
 * <p>
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-complement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC476 {

    public static int findComplement(int num) {
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            if (((1 << i) & num) == 0) {
                mask |= 1 << i;
            } else {
                mask = ~mask;
                break;
            }
        }
        return ~num & mask;
    }

    public static void main(String[] args) {
        System.out.println(findComplement(0));
    }
}
