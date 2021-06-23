package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LC401 {

    public List<String> readBinaryWatch(int turnedOn) {
        //边界：0<=小时<=3、0<=分钟<=5
        //=>总亮灯数 0<=turnedOn<=8
        List<String> ans = new ArrayList<>();
        if (turnedOn < 0 || turnedOn > 8) {
            return null;
        }
        for (int hour = 0; hour < 12; hour++) {
            for (int min = 0; min < 60; min++) {
                if (Integer.bitCount(hour) + Integer.bitCount(min) == turnedOn) {
                    ans.add(hour + ":" + (min < 10 ? "0" + min : min));
                }
            }
        }
        return ans;
    }
}
