package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/10 9:18:30
 * @description
 */
public class LC640 {
    public static String solveEquation(String equation) {
        int xCount = 0;
        int constant = 0;
        String[] split = equation.split("=");
        String left = split[0];
        String right = split[1];
        left = left.replaceAll("-", "+-");
        String[] leftArr = left.split("\\+");
        for (String s : leftArr) {
            if (s.length() == 0){
                continue;
            }
            if (s.contains("x")) {
                if (s.length() == 1) {
                    xCount++;
                } else if (s.equals("-x")) {
                    xCount--;
                } else {
                    xCount += Integer.parseInt(s.replace("x", ""));
                }
            } else {
                constant -= Integer.parseInt(s);
            }
        }
        right = right.replaceAll("-", "+-");
        String[] rightArr = right.split("\\+");
        for (String s : rightArr) {
            if (s.length() == 0){
                continue;
            }
            if (s.contains("x")) {
                if (s.length() == 1) {
                    xCount--;
                } else if (s.equals("-x")) {
                    xCount++;
                } else {
                    xCount -= Integer.parseInt(s.replace("x", ""));
                }
            } else {
                constant += Integer.parseInt(s);
            }
        }
        if (xCount == 0 && constant == 0) {
            return "Infinite solutions";
        } else if (xCount == 0) {
            return "No solution";
        } else {
            return "x=" + (constant / xCount);
        }
    }

    public static void main(String[] args) {
//        String s = "x+5-3+x=6+x-2";
        String s = "-x=-1";
        System.out.println("equation = " + s);
        System.out.println(solveEquation(s));
    }
}
