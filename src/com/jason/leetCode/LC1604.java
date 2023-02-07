package com.jason.leetCode;

import java.util.*;

/**
 * @author chenjieaj
 * @date 2023/2/7 11:00:58
 * @description
 */
public class LC1604 {
    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String k = keyName[i];
            List<Integer> list = map.getOrDefault(k, new ArrayList<>());
            list.add(getMinute(keyTime[i]));
            map.put(k, list);
        }
        List<String> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            List<Integer> time = map.get(key);
            Collections.sort(time);
            for (int i = 2; i < time.size(); i++) {
                if (time.get(i) - time.get(i - 2) <= 60) {
                    ans.add(key);
                    break;
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }

    private static Integer getMinute(String time) {
        return ((time.charAt(0) - '0') * 10 + time.charAt(1) - '0') * 60 + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
    }

    public static void main(String[] args) {
        String[] keyName = {"daniel","daniel","daniel","luis","luis","luis","luis"}, keyTime = {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"};
        System.out.println(alertNames(keyName, keyTime));
    }
}
