package com.jason.msjd;

public class MS_01_05 {
    public boolean oneEditAway(String first, String second) {
        int length1 = first == null ? 0 : first.length();
        int length2 = second == null ? 0 : second.length();
        if (length1 + length2 <= 1) {
            return true;
        } else if (length1 - length2 > 1 || length2 - length1 > 1) {
            return false;
        }
        boolean fond = false;
        if (length1 == length2) {
            for (int i = 0; i < length1; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (fond) {
                        // 第二次不同，
                        return false;
                    } else {
                        // 第一次不同
                        fond = true;
                    }
                }
            }
        } else {
            String longer = length1 > length2 ? first : second;
            String shorter = longer.equals(first) ? second : first;
            // shorter+一个是否能到匹配上longer
            for (int i = 0; i < Math.max(length1, length2); i++) {
                if (i == shorter.length() && !fond) {
                    return true;
                }
                if (longer.charAt(i) != shorter.charAt(i - (fond ? 1 : 0))) {
                    if (fond) {
                        return false;
                    } else {
                        fond = true;
                    }
                }
            }
        }
        return true;
    }
}
