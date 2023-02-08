package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2023/2/8 9:34:59
 * @description
 */
public class LC1233 {

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        int pre = 0;
        for (int i = 1; i < folder.length; i++) {
            if (folder[i].startsWith("/") && folder[pre].length() < folder[i].length() && folder[i].charAt(folder[pre].length()) == '/' && folder[i].startsWith(folder[pre])) {
                continue;
            }
            ans.add(folder[i]);
            pre = i;
        }
        return ans;
    }

}
