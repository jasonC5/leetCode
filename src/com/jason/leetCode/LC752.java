package com.jason.leetCode;

import java.util.*;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * <p>
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * <p>
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * <p>
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC752 {
    //bfs queue
    public static int openLock(String[] deadends, String target) {
        //边界条件
        if (target.equals("0000")) {
            return 0;
        }
        Set<String> deadStrs = new HashSet();
        for (String deadend : deadends) {
            deadStrs.add(deadend);
        }
        if (deadStrs.contains("0000")) {
            return -1;
        }
        //宽度优先遍历 bfs
        //初始状态
        Queue<String> q = new LinkedList();
        Set<String> s = new HashSet<>();
        q.offer("0000");
        s.add("0000");
        int step = 0;
        while (!q.isEmpty()) {
            //列举每种可能性（转一次）
            //在不在deadStrt里面，在不在已校验过的里面
            //不在的塞入q
            //判断是否得到target
            step++;
            //用快照，否则会一直循环
            int size = q.size();
            //把上一次产生的可能性都跑完，算一步
            for (int i = 0; i < size; i++) {
                String now = q.poll();
                //取出来一个，算可能性【8种】
                for (String next : possible(now)) {
                    if (!deadStrs.contains(next) && !s.contains(next)) {
                        q.offer(next);
                        s.add(next);
                    }
                    if (next.equals(target)) {
                        return step;
                    }
                }
            }
        }
        return -1;
    }

    private static List<String> possible(String str) {
        List<String> ans = new ArrayList<String>();
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            array[i] = c == '9' ? '0' : (char)(c + 1);
            ans.add(new String(array));
            array[i] = c == '0' ? '9' : (char)(c - 1);
            ans.add(new String(array));
            //恢复回去，否则下个有问题
            array[i] = c;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        System.out.println(openLock(deadends, target));
    }

}
