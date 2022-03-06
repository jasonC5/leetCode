package com.jason.jingsai.double73;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class Code2 {
    public static int[] sortJumbled(int[] mapping, int[] nums) {
        Map<String, Integer> idx = new HashMap<>();
        String[] targets = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            String target = map(mapping, nums[i]);
            idx.put(target, nums[i]);
            targets[i] = target;
        }
        Arrays.sort(targets, (s1, s2) -> Integer.parseInt(s1) - Integer.parseInt(s2));
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = idx.get(targets[i]);
        }
        return ans;
    }

    private static String map(int[] mapping, int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            sb.append(mapping[num]);
        } else {
            while (num > 0) {
                sb.append(mapping[num % 10]);
                num = num / 10;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        int[] mapping = {8, 9, 4, 0, 2, 1, 3, 5, 7, 6}, nums = {991, 338, 38};
        int[] mapping = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, nums = {0, 99999};
        System.out.println(Arrays.toString(sortJumbled(mapping, nums)));
    }


}
