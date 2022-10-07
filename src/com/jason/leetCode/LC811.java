package com.jason.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenjieaj
 * @date 2022/10/5 8:36:14
 * @description
 */
public class LC811 {
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> container = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] s = cpdomain.split(" ");
            Integer count = Integer.parseInt(s[0]);
            String url = s[1];
            // 当前域名
            container.put(url, container.getOrDefault(url, 0) + count);
            String[] split = url.split("\\.");
            if (split.length > 1) {
                String one = split[split.length - 1];
                container.put(one, container.getOrDefault(one, 0) + count);
            }
            if (split.length == 3) {
                String two = split[1] + "." + split[2];
                container.put(two, container.getOrDefault(two, 0) + count);
            }
        }
        return container.entrySet().stream().map(entry -> entry.getValue() + " " + entry.getKey()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"9001 discuss.leetcode.com"};
        System.out.println(subdomainVisits(strs));
    }
}
