package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组perm，它是前n个正整数的排列，且n是个 奇数。
 *
 * 它被加密成另一个长度为 n - 1的整数数组encoded，满足encoded[i] = perm[i] XOR perm[i + 1]。比方说，如果perm = [1,3,2]，那么encoded = [2,1]。
 *
 * 给你encoded数组，请你返回原始数组perm。题目保证答案存在且唯一。
 *
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1734 {
    public static void main(String[] args) {

        //1.n XOR n = 0
//        for (int i = 0; i < 10; i++) {
//            int x =(int) (Math.random() * Math.pow(10, 6));
//            System.out.println(x^x);
//        }
        // 数组perm，它是前n个正整数的排列，且n是个 奇数。
        // 它被加密成另一个长度为 n - 1的整数数组encoded
        // encoded[i] = perm[i] XOR perm[i + 1]
        // 推理：
        // encoded[i] ^ encoded[i+1] = perm[i] ^ perm[i + 1] ^ perm[i + 1] ^ perm[i+2] =  perm[i] ^ perm[i + 2]
        // 全部异或起来 =  perm[0] ^ perm[n]
        // 隔一个异或一个
        // encoded[i] ^ encoded[i+2] = perm[i] ^ perm[i + 1] ^ perm[i+2] ^ perm[i+3]
        // perm是奇数长度n，所以encoded是偶数长度n-1，即：0~n-2
        // encoded[1] ^ encoded[3] ^ encoded[5]  …… encoded[n-2] = perm[1] ^ perm[2] ^ perm[3] …… perm[n-1]
        //【重要信息：原数组是前n个正整数的排列组合  1、2、3、4……n】
        int[] decode = Solution.decode(new int[]{6,5,4,6});
        Arrays.stream(decode).boxed().forEach(System.out::print);
    }

    public static class Solution {
        public static int[] decode(int[] encoded) {
            int length = encoded.length;
            int[] decode = new int[encoded.length+1];
            //第一步，先找出decode[0]
            int xorAll = 0;
            for (int i = 0; i < length + 1; i++) {
                xorAll ^= i+1;
            }// perm[0] ^ perm[1] ^ perm[2] ^ perm[3] …… perm[n-1]
            int withOut0Xor = 0;
            for (int i = 1; i < length; i+=2) {
                withOut0Xor ^= encoded[i];
            }//perm[1] ^ perm[2] ^ perm[3] …… perm[n-1]
            decode[0] = xorAll ^ withOut0Xor;
            for (int i = 0; i < length; i++) {
                decode[i+1] = decode[i] ^ encoded[i];
            }
            return decode;
        }
    }


}
