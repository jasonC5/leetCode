package com.jason.leetCode;

import java.util.TreeMap;

public class LC726 {

    public class Info {
        public TreeMap<String, Integer> cntMap;
        public int end;

        public Info(TreeMap<String, Integer> count, int end) {
            this.cntMap = count;
            this.end = end;
        }
    }

    public String countOfAtoms(String formula) {
        char[] chars = formula.toCharArray();
        Info info = process(chars, 0);
        StringBuilder sb = new StringBuilder();
        TreeMap<String, Integer> map = info.cntMap;
        for (String key : map.keySet()) {
            sb.append(key);
            Integer num = map.get(key);
            if (num > 1) {
                sb.append(num);
            }
        }

        return sb.toString();
    }

    private Info process(char[] s, int i) {
        TreeMap<String, Integer> cntMap = new TreeMap<>();
        int cnt = 0;
        StringBuilder builder = new StringBuilder();
        Info info = null;
        while (i < s.length && s[i] != ')') {
            if (s[i] >= 'A' && s[i] <= 'Z' || s[i] == '(') {// 元素名开头
                // 先收集前面的
                if (builder.length() != 0 || info != null) {
                    cnt = cnt == 0 ? 1 : cnt;
                    // 单个元素
                    if (builder.length() != 0) {
                        String key = builder.toString();
                        cntMap.put(key, cntMap.getOrDefault(key, 0) + cnt);
                        builder.delete(0, builder.length());
                    } else {
                        // 括号内
                        for (String key : info.cntMap.keySet()) {
                            cntMap.put(key, cntMap.getOrDefault(key, 0) + info.cntMap.get(key) * cnt);
                        }
                        info = null;
                    }
                    cnt = 0;
                }
                // 前面的收集完了，初始化相关变量
                if (s[i] == '(') {
                    info = process(s, i + 1);
                    i = info.end + 1;
                } else {
                    builder.append(s[i++]);
                }
            } else if (s[i] >= 'a' && s[i] <= 'z') {// 元素名
                builder.append(s[i++]);
            } else {// 数字
                cnt = cnt * 10 + s[i++] - '0';
            }
        }
        if (builder.length() != 0 || info != null) {
            cnt = cnt == 0 ? 1 : cnt;
            if (builder.length() != 0) {
                String key = builder.toString();
                cntMap.put(key, cntMap.getOrDefault(key, 0) + cnt);
                builder.delete(0, builder.length());
            } else {
                for (String key : info.cntMap.keySet()) {
                    cntMap.put(key, cntMap.getOrDefault(key, 0) + info.cntMap.get(key) * cnt);
                }
                info = null;
            }
            cnt = 0;
        }
        return new Info(cntMap, i);
    }
}
