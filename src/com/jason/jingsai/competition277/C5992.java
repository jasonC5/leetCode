package com.jason.jingsai.competition277;

import java.util.Arrays;

/**
 * @author JasonC5
 */
public class C5992 {
    public int maximumGood1(int[][] statements) {
        int n = statements.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int p = process1(statements, 0, 1, new boolean[n]);
            ans = Math.max(ans, p);
        }
        return ans;
    }

    private int process1(int[][] statements, int cur, int goodNum, boolean[] visited) {
        visited[cur] = true;
//        statements.
        return 0;
    }

    public static int maximumGood(int[][] statements) {
        int n = statements.length;
        int ans = 0;
        int[] people = new int[n];//人，0坏人 ，1，好人，2未知
        Arrays.fill(people, 2);
        ans = Math.max(ans, process(statements, people, 0, 0));
        Arrays.fill(people, 2);
        ans = Math.max(ans, process(statements, people, 0, 1));
        return ans;
    }

    private static int process(int[][] statements, int[] people, int idx, int guess) {
        if (idx == people.length) {
            //收答案
            int ans = 0;
            for (int person : people) {
                if (person != 0) {
                    ans++;
                }
            }
            return ans;
        }
        if (people[idx] != 2 && people[idx] != guess) {
            return 0;
        }
        people[idx] = guess;
        if (guess == 1) {// 认为是好人，核对供词，如果和现有的冲突，则认为路线错误
            for (int i = 0; i < people.length; i++) {
                if (statements[idx][i] != 2) {
                    if (people[i] == 2) {
                        people[i] = statements[idx][i];
                    } else if (people[i] != statements[idx][i]) {
                        return 0;
                    }
                }
            }
        }//坏人的供词不用考虑
        int[] cp1 = Arrays.copyOf(people, people.length);
        int[] cp2 = Arrays.copyOf(people, people.length);
        int p1 = process(statements, cp1, idx + 1, 0);
        int p2 = process(statements, cp2, idx + 1, 1);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
//        int[][] a = {{2, 0, 2, 2, 0}, {2, 2, 2, 1, 2}, {2, 2, 2, 1, 2}, {1, 2, 0, 2, 2}, {1, 0, 2, 1, 2}};
        int[][] a = {{2, 0, 2, 2, 0}, {2, 2, 2, 1, 2}, {2, 2, 2, 1, 2}, {1, 2, 0, 2, 2}, {1, 0, 2, 1, 2}};
//        int[][] a = {{2, 2}, {1, 2}};
        System.out.println(maximumGood(a));
    }
}
