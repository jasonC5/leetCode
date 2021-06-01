package com.jason.leetCode;

import java.util.Arrays;

/**
 * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 * 给你一个下标从 0 开始的正整数数组candiesCount，其中candiesCount[i]表示你拥有的第i类糖果的数目。同时给你一个二维数组queries，其中queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]。
 *
 * 你按照如下规则进行一场游戏：
 *
 * 你从第0天开始吃糖果。
 * 你在吃完 所有第 i - 1类糖果之前，不能吃任何一颗第 i类糖果。
 * 在吃完所有糖果之前，你必须每天 至少吃 一颗糖果。
 * 请你构建一个布尔型数组answer，满足answer.length == queries.length 。answer[i]为true的条件是：在每天吃 不超过 dailyCapi颗糖果的前提下，你可以在第favoriteDayi天吃到第favoriteTypei类糖果；否则 answer[i]为 false。注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 *
 * 请你返回得到的数组answer。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * 输出：[true,false,true]
 * 提示：
 * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
 * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
 * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
 * 示例 2：
 *
 * 输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
 * 输出：[false,true,true,false,false]
 *
 *
 * 1 <= candiesCount.length <= 105
 * 1 <= candiesCount[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 3
 * 0 <= favoriteTypei < candiesCount.length
 * 0 <= favoriteDayi <= 109
 * 1 <= dailyCapi <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1744 {
    public static void main(String[] args) {
        int[] candiesCount = {24,41,16,36,6,38,8,43,43,45,17,13,4,44,43,6,10,38,5,23,3,30,5,14,20,31,24,19,32,7,3,20,15,46,8,39,18,21,41,48,39,26,16,46,6,13,10,18,46,25,28,34,31,26,13,8,32,32,49,26,7,46,19,2,24,19,26,22,39,24,48,10,17,10,38,41,48,1,29,30,1,1,27,36,29,37,11,31,5,24,9,33,9,36,3,33,46,49,36}
                ;
        int[][] queries = {{58,1309,32},{15,68,20},{89,697,6},{89,1419,81},{33,1777,22},{51,677,79},{33,271,66},{6,1404,19},{85,1093,51},{77,1950,60},{22,1850,76},{89,754,100},{24,1352,98},{53,2273,75},{91,1190,13},{14,599,25},{52,1170,97},{60,801,14},{21,585,92},{92,601,35},{56,1502,62},{59,2028,10},{75,1038,96},{30,1724,29},{91,796,91},{76,1710,93},{12,681,49},{94,1450,38},{69,1574,53},{57,15,21},{64,954,18},{79,2274,98},{51,1615,48},{66,2343,52},{16,1370,49},{37,1434,58},{88,2049,55},{92,1621,72},{38,2338,54},{73,1815,52},{10,470,11},{93,1854,76},{75,1106,56},{77,2199,42},{77,896,74},{48,873,4},{64,1050,93},{64,489,76},{12,1587,27},{84,1491,13},{35,262,66},{56,255,51},{5,132,2},{37,1863,2},{79,1098,33},{50,287,56},{77,299,56},{65,939,35},{14,1765,79},{61,2402,75},{86,2038,69},{78,1763,3},{39,466,2},{92,182,45},{65,1657,95},{74,1013,36},{75,264,5},{48,1773,68},{52,485,60},{24,862,52},{48,1831,82},{61,2367,17},{0,1233,37},{72,432,46},{36,412,34},{95,1514,61},{41,767,60},{16,178,50},{7,159,88},{41,1216,15},{21,955,40},{68,217,15},{52,2292,81},{51,688,10},{36,601,58},{62,2057,60},{42,928,71}};
        boolean[] booleans = canEat(candiesCount, queries);
        print(booleans);
        //对数器
        boolean[] booleans2 = canEat2(candiesCount, queries);
        print(booleans2);
        System.out.println(compare(booleans,booleans2));
    }

    private static boolean compare(boolean[] booleans, boolean[] booleans2) {
        if (booleans.length != booleans2.length) {
            return false;
        }
        for (int i = 0; i <booleans.length; i++) {
            if (booleans[i] != booleans2[i]) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    private static void print(boolean[] booleans) {
        for (boolean aBoolean : booleans) {
            System.out.print(aBoolean + "\t");
        }
        System.out.println();
    }

    public static boolean[] canEat(int[] candiesCount, int[][] queries) {
        //candiesCount 第i位表示第i中糖果的数量
        //queries 三元组数组，每一个三元组是一个问题：三元组中：0 - 第几种糖果x  1 - 第几天y  2 - 每天最多吃糖果数量z  ，
        // 每天最少吃1颗糖，多吃z颗糖的情况下，第y天能不能吃到第x种糖果【第0天开始吃，按种类数量开始吃，每天至少吃一颗糖】
        //返回每个问题的答案
        //前缀和
        int length = candiesCount.length;
        //用long防溢出
        long[] sum = new long[length];
        sum[0] = candiesCount[0];
        for (int i = 1; i < length; i++) {
            //i之前（包含i）的糖果总数
            sum[i] = sum[i-1] + candiesCount[i];
        }
        int qLength = queries.length;
        //答案
        boolean[] ans = new boolean[qLength];
        for (int i = 0; i < qLength; i++) {
            if (i == 62) {
                System.out.println(i);
            }
            int[] query = queries[i];
            //三元组中：0 - 第几种糖果x  1 - 第几天y  2 - 每天最多吃糖果数量z  ，
            int x = query[0];//能吃到第几种糖果
            int y = query[1];//第几天
            int z = query[2];//每天最多吃几颗糖果
            //第一种情况：i==0 第0天，
//            if (y == 0) {
//                //第0天能吃0~z个，
//                ans[i] = x==0?true : z >= sum[x-1];
//            } else {
                //y天最多吃糖果 y*z 最少吃糖果： y  吃到x种糖果：sum[x-1] 吃完x种糖果：sum[x]
                ans[i] = x==0 ? (sum[0] >= y+1) : (sum[x] >= y+1 && sum[x-1] < ((long)(y+1) * (long)z));
//            }
        }
        return ans;
    }

    //标准答案，对数器
    public static boolean[] canEat2(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;

        // 前缀和
        long[] sum = new long[n];
        sum[0] = candiesCount[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }

        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; ++i) {
            int[] query = queries[i];
            int favoriteType = query[0], favoriteDay = query[1], dailyCap = query[2];

            long x1 = favoriteDay + 1;//每天吃一个，
            long y1 = (long) (favoriteDay + 1) * dailyCap;//每天吃z个
            long x2 = favoriteType == 0 ? 1 : sum[favoriteType - 1] + 1;//
            long y2 = sum[favoriteType];//吃完x种糖果

            ans[i] = !(x1 > y2 || y1 < x2);
        }
        return ans;
    }
}
