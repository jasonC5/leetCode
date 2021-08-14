package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JasonC5
 */
public class LC1583 {

    public static void main(String[] args) {
        int n = 4;
        int[][] pre = {{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}};
        int[][] pairs = {{1, 3}, {0, 2}};
        System.out.println(unhappyFriends(n, pre, pairs));
    }

    public static int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] happy = new int[n];
        int[] pairsMap = new int[n];
        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {
            pairsMap[pairs[i][0]] = pairs[i][1];
            pairsMap[pairs[i][1]] = pairs[i][0];
        }
        //亲密度,越小，亲密度越高
        Map<String, Integer> intimacyMap = new HashMap<>();
        for (int i = 0; i < preferences.length; i++) {
            for (int j = 0; j < preferences[0].length; j++) {
                intimacyMap.put(i + "_" + preferences[i][j], j);
            }
        }
        //所有配对关系，存到pairsMap里了
        for (int i = 0; i < pairsMap.length; i++) {
            //已经算过了
            if (happy[i] != 0) {
                continue;
            }
            //跟我配对的人
            int pair = pairsMap[i];
            //我的亲近优先级
            int[] preference = preferences[i];
            for (int friend : preference) {
                //前面的朋友都过了，那我是快乐的
                if (pair == friend) {
                    happy[i] = 1;
                    break;
                }
                //当前跟我配对的人，亲密度比friend低
                int friendPair = pairsMap[friend];
                //我比这个朋友的亲密度高，那我不快乐
                if (intimacyMap.get(friend + "_" + i) < intimacyMap.get(friend + "_" + friendPair)) {
                    happy[i] = 2;
                    ans++;
                    //朋友之前没算过，也不快乐
                    if (happy[friend] == 0) {
                        happy[friend] = 2;
                        ans++;
                    }
                    break;
                }
            }
        }
        return ans;
    }
}
