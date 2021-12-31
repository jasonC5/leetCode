package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC1419 {
    public static char[] CROAK = {'c', 'r', 'o', 'a', 'k'};

    public static int minNumberOfFrogs(String croakOfFrogs) {
        List<Integer> frogs = new ArrayList<>();
        int nums = 0;
        char[] chars = croakOfFrogs.toCharArray();
        for (char c : chars) {
            if (c == CROAK[0]) {
                frogs.add(1);
            } else {
                boolean match = false;
                for (int i = 0; i < frogs.size(); i++) {
                    if (c == CROAK[frogs.get(i)]) {
                        Integer idx = frogs.remove(i);
                        if (++idx < CROAK.length) {
                            frogs.add(idx);
                        }
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    return -1;
                }
            }
            nums = Math.max(nums, frogs.size());
        }
        if (frogs.size() > 0) {
            return -1;
        }
        return nums;
    }

    public static void main(String[] args) {
        String s = "croakcroa";
        System.out.println(minNumberOfFrogs(s));
    }
}
