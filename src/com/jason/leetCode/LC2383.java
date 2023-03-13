package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/13 9:13:09
 * @description
 */
public class LC2383 {
    public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int add = 0;
        int n = energy.length;
        for (int i = 0; i < n; i++) {
            if (initialEnergy > energy[i]) {
                initialEnergy -= energy[i];
            } else {
                add += energy[i] - initialEnergy + 1;
                initialEnergy = 1;
            }
            if (initialExperience > experience[i]) {
                initialExperience += experience[i];
            } else {
                add += experience[i] - initialExperience + 1;
                initialExperience = experience[i] + 1 + experience[i];
            }
        }
        return add;
    }
}
