package com.jason.leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author chenjieaj
 * @date 2023/2/12 21:59:53
 * @description
 */
public class LC1138 {
    class Solution {

        public String alphabetBoardPath(String target) {
            int cx = 0, cy = 0;
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                int nx = (c - 'a') / 5;
                int ny = (c - 'a') % 5;
                if (nx < cx) {
                    for (int j = 0; j < cx - nx; j++) {
                        res.append('U');
                    }
                }
                if (ny < cy) {
                    for (int j = 0; j < cy - ny; j++) {
                        res.append('L');
                    }
                }
                if (nx > cx) {
                    for (int j = 0; j < nx - cx; j++) {
                        res.append('D');
                    }
                }
                if (ny > cy) {
                    for (int j = 0; j < ny - cy; j++) {
                        res.append('R');
                    }
                }
                res.append('!');
                cx = nx;
                cy = ny;
            }
            return res.toString();
        }
    }
}
