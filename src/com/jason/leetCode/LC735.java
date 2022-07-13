package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author chenjieaj
 * @date 2022/7/13 9:00:14
 * @description
 */
public class LC735 {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        // true 右-正数，false 左-负数
        Boolean flag = null;
        Integer mask = 1 << 31;
        for (int asteroid : asteroids) {
            boolean cur = (asteroid & mask) == 0;// 是否时正数
            if (flag == null) {
                flag = cur;
                stack.push(asteroid);
            } else if (!flag || cur) {// 前面的往左，或者当前的往右，都不会撞
                flag = cur;
                stack.push(asteroid);
            } else {// 前面的往右，当前往左，一定会撞
                while (!stack.isEmpty()) {
                    Integer pop = stack.pop();
                    if (asteroid + pop == 0) {
                        // 同归于尽了
                        break;
                    } else if ((asteroid & mask) == (pop & mask)) {
                        // 相同方向
                        stack.push(pop);
                        stack.push(asteroid);
                        break;
                    } else if (asteroid + pop > 0) {
                        //被左边撞碎，重新塞回去，
                        stack.push(pop);
                        break;
                    } else if (stack.isEmpty()) {
                        // 把左边撞碎了，但是容器空了，把自己塞进去
                        stack.push(asteroid);
                        break;
                    }
                    // 把左边撞碎，继续撞
                }
                // 重新看下最右边的方向
                if (stack.isEmpty()) {
                    flag = null;
                } else {
                    flag = (stack.peek() & mask) == 0;
                }
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] asteroids = {1, -2, -2, -2};
        System.out.println(Arrays.toString(asteroidCollision(asteroids)));
    }
}
