package com.jason.msjd;

/**
 * https://leetcode-cn.com/problems/is-unique-lcci/
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * @author JasonC5
 */
public class M001 {

    public static void main(String[] args) {
        System.out.println(isUnique("abc"));
        System.out.println(isUnique("chenjie"));
    }

    public static boolean isUnique(String astr) {
        //位运算实现类似容器的功能，字符一共24位，可以用int的位数来标识
        int bitmap = 0;//有局限性,只能是小写字母
        for (char c : astr.toCharArray()) {
            int seek = c - 'a';
            if (((bitmap >> seek) & 1) == 1) {
                return false;
            } else {
                bitmap |= (1 << seek);
            }
        }
        return true;
    }
}
