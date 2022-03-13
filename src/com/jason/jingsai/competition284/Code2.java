package com.jason.jingsai.competition284;

import java.util.HashMap;
import java.util.Map;

public class Code2 {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        Map<Long, Integer> map = new HashMap<>();
        int[] workpiece = new int[artifacts.length];//每个工件占几块
        for (int i = 0; i < artifacts.length; i++) {
            for (int row = artifacts[i][0]; row <= artifacts[i][2]; row++) {
                for (int col = artifacts[i][1]; col <= artifacts[i][3]; col++) {
                    map.put((long) row << 32 | col, i);
                    workpiece[i]++;
                }
            }
        }
        int ans = 0;
        for (int[] ints : dig) {
            long key = (long) ints[0] << 32 | ints[1];
            if (map.containsKey(key)){
                Integer idx = map.get(key);
                if (--workpiece[idx] == 0){
                    ans++;
                }
            }
        }
        return ans;
    }
}
