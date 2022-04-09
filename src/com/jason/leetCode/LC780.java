package com.jason.leetCode;

public class LC780 {

    public static boolean reachingPoints0(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        } else if (sx > tx || sy > ty) {
            return false;
        }
        return reachingPoints0(sx, sx + sy, tx, ty) || reachingPoints0(sx + sy, sy, tx, ty);
    }

    public static boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        int max;
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty) {
                return true;
            } else if (sx > tx || sy > ty) {
                return false;
            }
            max = Math.max(tx, ty);
            tx = tx == max ? (tx % ty) : tx;
            if (tx == 1) {
                return true;
            }
            ty = ty == max ? (ty % tx) : ty;
            if (ty == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (sx == tx && sy == ty) {
            return true;
        } else if (sx == tx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (sy == ty) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        System.out.println(reachingPoints0(1, 1, 3, 5));
        System.out.println(reachingPoints(1, 1, 3, 5));
    }
}
