package com.jason.jingsai.double72;

import java.util.ArrayList;
import java.util.List;

public class C5998 {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if ((finalSum & 1) == 1) {
            return ans;
        }
        for (long i = 2; finalSum > 0; i += 2) {
            long sub = finalSum > i << 1 ? i:finalSum;
            ans.add(sub);
            finalSum -= sub;
        }
        return ans;
    }
}
