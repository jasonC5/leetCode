package com.jason.jingsai.competition278;

public class C5994 {
    public static String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long[] pows = new long[k];
        pows[0] = 1;
        for (int i = 1; i < k; i++) {
            pows[i] = (pows[i - 1] * power) % modulo;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - k; i++) {
            String sbs;
            if (val(sbs = s.substring(i, i + k), pows, modulo) == hashValue) {
                return sbs;
            }
        }
        return "";
    }

    private static int val(String subStr, long[] pows, int modulo) {
        long res = 0;
        for (int i = 0; i < subStr.length(); i++) {
            res += (subStr.charAt(i) - 'a' + 1) * pows[i];
            res %= modulo;
        }
        return (int) (res % modulo);
    }

    public static void main(String[] args) {
        String s = "xmmhdakfursinye";
        int power = 96, modulo = 45, k = 15, hashValue = 21;
        System.out.println(subStrHash(s, power, modulo, k, hashValue));
    }
}
