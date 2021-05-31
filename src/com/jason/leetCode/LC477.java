package com.jason.leetCode;

/**
 * 两个整数的汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 *
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 *
 * 示例:
 *
 * 输入: 4, 14, 2
 *
 * 输出: 6
 *
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 *
 * 数组中元素的范围为从0到10^9。
 * 数组的长度不超过10^4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/total-hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC477 {
    public static void main(String[] args) {
        int[] a = {4,14,2};
        System.out.println(Solution.totalHammingDistance(a));
    }

    static class Solution {
        public static int totalHammingDistance(int[] nums) {
            //按位来算，如果只有一位，数组长度为n，所有元素不是1就是0，
            //此时：数组的任意两个数之间的明汉距离之和 = （1的数量）*（0的数量）
            //（任意取一个1，和一个0，明汉距离为1，其他所有情况都为0）
            //换到数组中都是正常整数，只需要把每一位都按上面的步骤相加即可
            int length = nums.length;
            int ans = 0;
            for (int i = 0; i < 31; i++) {//0到 10^9  2^0 ~ 2^30 所以到30就够了
                int count = 0;//
                int mask = 1<<i;//
                for (int j = 0; j < length; j++) {
                    if ((nums[j] & mask) != 0){
                        count ++;//1的数量
                    }
                }
                ans += count * (length - count);//1的数量 * 0的数量
            }
            return ans;
        }
    }
}
