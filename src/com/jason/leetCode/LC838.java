package com.jason.leetCode;

import java.util.*;

public class LC838 {
    public static String pushDominoes(String dominoes) {
        Queue<int[]> actions = new LinkedList<>();
        char[] chars = dominoes.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (chars[i] == 'L') {
                actions.add(new int[]{i, 0});
            } else if (chars[i] == 'R') {
                actions.add(new int[]{i, 1});
            }
        }
        Arrays.fill(chars, '.');
        int loop = 0;
        while (!actions.isEmpty()) {
            loop++;
            int size = actions.size();//按层，每次从左往右来一遍
            for (int i = 0; i < size; i++) {//每次从左往右处理
                int[] act = actions.poll();
                if (chars[act[0]] == '.' && !(act[0] > 0 && act[0] < length - 1 && chars[act[0] - 1] == 'R' && chars[act[0] + 1] == 'L')) {
                    if (act[1] == 0 ) {
                        chars[act[0]--] = 'L';
                        if (act[0] >= 0) {
                            actions.add(act);
                        }
                    } else {
                        chars[act[0]++] = 'R';
                        if (act[0] < length) {
                            actions.add(act);
                        }
                    }
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes(dominoes));
    }
}
