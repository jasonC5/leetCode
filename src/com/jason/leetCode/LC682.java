package com.jason.leetCode;

public class LC682 {
    public static int calPoints(String[] ops) {
        int[] points = new int[ops.length];
        int sp = 0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                points[sp] = points[sp - 1] + points[sp++ - 2];
            } else if (ops[i].equals("D")) {
                points[sp] = points[sp++ - 1] << 1;
            } else if (ops[i].equals("C")) {
                sp--;
            } else {
                points[sp++] = Integer.parseInt(ops[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < sp; i++) {
            ans += points[i];
        }
        return ans;
    }

    public static void main(String[] args) {
//        String[] ops = {"5", "2", "C", "D", "+"};
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(ops));
    }
}
