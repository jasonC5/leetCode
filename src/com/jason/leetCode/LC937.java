package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
public class LC937 {
    public static String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        List<String> numLogs = new ArrayList<>();
        List<String> strLogs = new ArrayList<>();
        for (String log : logs) {
            int fsd = log.indexOf(" ");
            char delegate = log.charAt(fsd + 1);
            if (delegate >= '0' && delegate <= '9') {
                numLogs.add(log);
            } else {
                strLogs.add(log);
            }
        }
        strLogs.sort((s1, s2) -> {
            String ns1 = s1.substring(s1.indexOf(" ") + 1);
            String ns2 = s2.substring(s2.indexOf(" ") + 1);
            return ns1.equals(ns2) ? s1.compareTo(s2) : ns1.compareTo(ns2);
        });
        int strLogSize = strLogs.size();
        for (int i = 0; i < strLogSize; i++) {
            logs[i] = strLogs.get(i);
        }
        for (int i = strLogSize; i < length; i++) {
            logs[i] = numLogs.get(i - strLogSize);
        }
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(reorderLogFiles(logs)));
    }
}
