package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/8/16 12:06:04
 * @description
 */
public class LC1656 {
    class OrderedStream {
        String[] container;
        int ptr;

        public OrderedStream(int n) {
            ptr = 1;
            container = new String[n + 1];
        }

        public List<String> insert(int idKey, String value) {
            container[idKey] = value;
            List<String> ans = new ArrayList<>();
            while (ptr < container.length && container[ptr] != null) {
                ans.add(container[ptr++]);
            }
            return ans;
        }
    }
}
