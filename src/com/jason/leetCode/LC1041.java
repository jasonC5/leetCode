package com.jason.leetCode;

public class LC1041 {
    public static boolean isRobotBounded(String instructions) {
        // 每次向右转 之后，如果 go x和y 的偏移
        int[][] go = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        // 初始位置和方向
        int x = 0, y = 0, direction = 0;
        char[] chars = instructions.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'R':
                    direction = (direction + 1) % 4;
                    break;
                case 'L':
                    direction = (direction + 3) % 4;
                    break;
                case 'G':
                    x += go[direction][0];
                    y += go[direction][1];
                    break;
                default:
            }
        }
        // 一次直接回到原点（每次循环都能回到原点），或者一次结束时候方向不是面朝北方（经过若干次一定能回来）
        return (x == 0 && y == 0) || direction != 0;
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
    }
}
