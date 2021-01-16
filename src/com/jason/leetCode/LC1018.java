package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 *
 * 示例 1：
 *
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1018 {

    public static void main(String[] args) {
//        int[] A = {0,1,1};
        int[] A = {0,1,1,1,1,1};
        List<Boolean> booleans = prefixesDivBy5(A);
        System.out.println(booleans);
    }


    public static List<Boolean> prefixesDivBy5(int[] A) {
        //方案1，每个都算成10进制，然后%5
        //方案2，N[i+1] = N[i]*2+A[i];
        int ni = 0;
        List<Boolean> answer = new ArrayList<>();
        for (int i = 0; i<A.length; i++) {
//            ni = ((ni<<1) + A[i]);//可能会溢出
            //把可以被5整除的部分减掉，
            // n[i] = (5*X) + ni%5
            // n[i+1] = n[i] * 2 + Ai
            // n[i+1] = [(5*X) + ni%5] * 2 + Ai
            // n[i+1]%5 = {[(5*X) + ni%5] * 2 + Ai}%5
            // n[i+1]%5 = [(10*X) + (ni%5)*2 + Ai]%5
            // n[i+1]%5 = (10*X)%5【=0】 + [(ni%5)*2 + Ai]%5
            // n[i+1]%5 =  [(ni%5)*2 + Ai]%5
            ni = (ni*2 +A[i])%5;
            answer.add(ni==0);
        }
        return answer;
    }
}
