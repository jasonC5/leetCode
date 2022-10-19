package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/10/19 8:58:01
 * @description
 */
public class LC1700 {
    public static int countStudents(int[] students, int[] sandwiches) {
        int n = students.length;
        int[] ctn = new int[2];
        ctn[1] = Arrays.stream(students).sum();
        ctn[0] = n - ctn[1];
        for (int sandwich : sandwiches) {
            if (ctn[sandwich] == 0) {
                break;
            }
            ctn[sandwich]--;
        }
        return ctn[0] + ctn[1];
    }

    public static void main(String[] args) {
        int[] stu = {1, 1, 0, 0}, sandwiches = {0, 1, 0, 1};
        System.out.println(countStudents(stu, sandwiches));
    }
}
