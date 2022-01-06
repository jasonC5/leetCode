package com.jason.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class LC71 {
    public static String simplifyPath(String path) {
        path+="/";
        char[] chars = path.toCharArray();
        StringBuilder pre = new StringBuilder();
        Deque<String> heap = new LinkedList<>();
        for (char c : chars) {
            if (c == '/') {
                if ("..".equals(pre.toString())) {
                    //往上返回一层
                    heap.pollLast();
                } else if (!"".equals(pre.toString()) && !".".equals(pre.toString())) {
                    heap.addLast(pre.toString());
                }
                //归零
                pre.reverse();
            } else {
                pre.append(c);
            }
        }
//        if (pre.length() != 0) {
//            if ("..".equals(pre.toString())) {
//                //往上返回一层
//                heap.pollLast();
//            } else if (!"".equals(pre.toString()) && !".".equals(pre.toString())) {
//                heap.addLast(pre.toString());
//            }
//        }
        StringBuilder ans = new StringBuilder();
        while (!heap.isEmpty()) {
            ans.append("/").append(heap.pollFirst());
        }
        return ans.length() == 0 ? "/" : ans.toString();
    }

    public static void main(String[] args) {
        String path = "/a/./b/../../c";
        System.out.println(simplifyPath(path));
    }

}
