package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JasonC5
 */
public class LC1441 {
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int pre = 0;
        for (int i = 0; i < target.length; i++) {
            // push
            int cur = pre;
            for (; cur < target[i] - 1; cur++) {
                ans.add("push");
            }
            // pop
            for (; pre < cur; pre++) {
                ans.add("pop");
            }
            pre++;
            // push
            ans.add("push");
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] target = {1, 2};
        int n = 4;
        System.out.println(buildArray(target, n));
    }
}
