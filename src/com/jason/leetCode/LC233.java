package com.jason.leetCode;

/**
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC233 {

    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
    }

    public static int countDigitOne(int n) {
        int ans = 0;
        int mask = 1;
        //前一个十进制位，包含1的个数
        int preCount = 0;
        while (n >= mask) {
//            ans += (n / (mask * 10)) * mask + Math.min(Math.max(n % (mask * 10) - mask + 1, 0), mask);
            //156958，第三位时，固定第三位为1，前面0~155的时候，后面00~99 100个=mask
            ans += (n / (mask * 10)) * mask;
            //前面为156时，当前位>1的时候 后面00~99,=1的时候%mask，==0的时候0
            int i = (n / mask) % 10;
            //根据当前位是啥，加上剩下的--当前位置是0，不能是1，0中，当前位置是1，0~n % (mask)，当前位置>1：0~999999  mask
            ans += (i == 0 ? 0 : (i == 1 ? n % (mask) + 1 : mask));
            mask *= 10;
        }
        return ans;
    }
}
