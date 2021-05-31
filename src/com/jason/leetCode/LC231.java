package com.jason.leetCode;

/**
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 *
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 *
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 *
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 *
 * 输入：n = 5
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC231 {

    public static void main(String[] args) {
//        isPowerOfTwo2(-16);
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int num = (int)((Math.random() * Integer.MAX_VALUE) - (Math.random() * Integer.MAX_VALUE));
            boolean ans1,ans2;
            if ((ans1 = isPowerOfTwo2(num)) != (ans2 = isPowerOfTwo(num))) {
                System.out.println("error…… num="+num+",ans1="+ans1+",ans2="+ans2);
                return;
            }
        }
        System.out.println("complete!!!!!!!");
    }

    public static boolean isPowerOfTwo2(int n) {
        //统计二进制上的1，若是2的几次幂，则有且只有一个1
        if(n <= 0){
            return false;
        }
        int count = 0;
        while (n!=0){
            count += n&1;
            n>>>=1;
        }
        return count == 1;
    }

    public static boolean isPowerOfTwo(int n) {
        //假设n是2的K次幂，则在二进制上，第K+1位为1，其余数字都为0
        //n-1 在二进制上 前k位都为1，后面都为0，则n&(n-1)=0
        // return n>0 && (n & (n-1))==0;
        //方案2：-n = ~n+1 n & (-n) 能获取到二进制上最右边的一位，若n本身就是2的幂次方，则结果 = n
        return n>0 && (n&-n) == n;
    }
}
