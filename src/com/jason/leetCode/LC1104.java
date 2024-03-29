package com.jason.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按“之” 字形进行标记。
 * <p>
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * <p>
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * <p>
 * <p>
 * <p>
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 示例 2：
 * <p>
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1104 {
    public static void main(String[] args) {
//        System.out.println(findLevel(1));
//        System.out.println(findLevel(2));
//        System.out.println(findLevel(3));
//        System.out.println(findLevel(4));
//        System.out.println(findLevel(5));
//        System.out.println(findLevel(6));
//        System.out.println(findLevel(7));
//        System.out.println(findLevel(8));
//        System.out.println(findLevel(9));

        System.out.println(pathInZigZagTree(14));
        System.out.println(pathInZigZagTree(26));
    }

    public static List<Integer> pathInZigZagTree(int label) {
        //先求第几层
        int level = findLevel(label);
        //每一行都是反的，求它原本应该在的位置
//        List<Integer> ans = new ArrayList<Integer>(new int[level]);
        int[] ans = new int[level];
        while (label > 0) {
            ans[level - 1] = label;
            label = (((1 << level) - 1) + (1 << level - 1) - label) / 2;
            level--;
        }
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    private static int findLevel(int num) {
        int mask = 1;
        int level = 1;
        while ((mask << level) <= num) {
            level++;
        }
        return level;
    }

}
