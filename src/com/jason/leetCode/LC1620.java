package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/11/2 8:51:47
 * @description
 */
public class LC1620 {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int x1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y1 = Integer.MAX_VALUE, y2 = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            x1 = Math.min(tower[0], x1);
            x2 = Math.max(tower[0], x2);
            y1 = Math.min(tower[1], y1);
            y2 = Math.max(tower[1], y2);
        }
        int maxPower = 0;
        int ansX = 0, ansY = 0;
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                int power = 0;
                int[] point = {x, y};
                for (int[] tower : towers) {
                    int value = getSquaredDistance(point, tower);
                    if (value <= radius * radius) {
                        double distance = Math.sqrt(value);
                        power += (int) Math.floor(tower[2] / (1 + distance));
                    }
                }
                if (power > maxPower) {
                    ansX = x;
                    ansY = y;
                    maxPower = power;
                }
            }
        }
        return new int[]{ansX, ansY};
    }

    public int getSquaredDistance(int[] coordinate, int[] tower) {
        return (tower[0] - coordinate[0]) * (tower[0] - coordinate[0]) + (tower[1] - coordinate[1]) * (tower[1] - coordinate[1]);
    }

    public static void main(String[] args) {
        int[][] towers = {{23, 11, 21}};
        int radius = 9;
        LC1620 lc1620 = new LC1620();
        System.out.println(Arrays.toString(lc1620.bestCoordinate(towers, radius)));
    }
}
