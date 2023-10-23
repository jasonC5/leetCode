package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/10/23 9:43:49
 * @description
 */
public class LC2678 {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (String detail : details) {
            ans += Integer.parseInt(detail.substring(11, 13)) > 60 ? 1 : 0;
        }
        return ans;
    }
}
