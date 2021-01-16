package com.jason.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 归并及问题
 */
public class LC947 {

    public static void main(String[] args) {
        int[][] stones = {{0,1},{1,0}};
        int removeStones = removeStones(stones);
//        int removeStones = removeStones2(stones);
        System.out.println(removeStones);
    }

    public static int removeStones(int[][] stones) {
        //可移除石子的最大数量
        //理解：同行或者同列有其他石头存在，就可以移除
        //也就是说，如果有多个坐标点相连，最终最多可以删除到只剩一个点,
        //题干转换：这些左边能组成x个相连的树，则最多可删除掉 n-x 个点，剩n个点
        //归并集问题


        //和小岛问题，是一个问题：二维数组，0、1  1相邻的组成1个小岛，一共有多少个小岛x  这题是n-x
        // （不一样，条件不同，小岛问题：x=x1 && Math.abs(y-y1)==1  ||  y=y1 && Math.abs(x-x1)==1  这个问题：x==x1 || y==y1 ）
        //
        if(stones.length < 2){
            return 0;
        }

        List<List<int[]>> landList = new ArrayList();//一共有多少个的小岛
        for(int i=0; i< stones.length;i++){
            int[] point = stones[i];
            // int x = point[0];
            // int y = point[1];
            if(landList.size()==0){//第一个小岛
                List<int[]> land = new ArrayList();
                land.add(point);
                landList.add(land);
            } else {
                dealPoint(landList, point);
            }
        }
        return (stones.length  - landList.size() );
    }

    static void dealPoint(List<List<int[]>> landList, int[] point){
        List<List<int[]>> removeLand= new ArrayList();//被合并小岛
        // List<int[]> newLand= new ArrayList();//合并出来的新小岛
        List<int[]> mergeLand = null;//合并出来的新小岛
        Boolean newLand = true;
        int x = point[0];
        int y = point[1];
        for(List<int[]> land:landList){
            for(int[] landPoint:land){
                int landX = landPoint[0];
                int landY = landPoint[1];
                if(x==landX || y==landY){//相邻点
                    newLand = false;//不是新孤岛了
                    if(mergeLand == null){
                        land.add(point);
                        mergeLand = land;
                        break;//跟这个小岛相连了，不用再往下查这个小岛了
                    } else {
                        //合并
                        mergeLand.addAll(land);
                        removeLand.add(land);
                        break;
                    }
                }
            }
        }
        if(removeLand.size() > 0){
            landList.removeAll(removeLand);
        }
        if(newLand){//没有邻岛，新小岛-孤岛
            List<int[]> singleLand = new ArrayList();
            singleLand.add(point);
            landList.add(singleLand);
        }
    }

    //优化方案：二维转一维，并查集
    //这个点如果能找到归并的点，则该x轴上所有的点都归并到这个点，同时y轴上所有的点也归并的这个点

    //找不到归并的点，则添加一个节点，归并到自己
    public int removeStones2(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            // 下面这三种写法任选其一
//             unionFind.union(~stone[0], stone[1]);
            // unionFind.union(stone[0] - 10001, stone[1]);
            unionFind.union(stone[0] + 10001, stone[1]);//二维转一维  (0,0)  (10001, 0)
        }
        return stones.length - unionFind.getCount();
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {//找不到这个点的根，添加一个节点
                parent.put(x, x);//0->0
                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                count++;
            }

            if (x != parent.get(x)) {//如果不是指向自己（不是新节点），递归找到根
                parent.put(x, find(parent.get(x)));//
            }
            return parent.get(x);//返回根
        }

        public void union(int x, int y) {// 10001 0   10002 0
            int rootX = find(x);//10001 count++  10002  count++     10001       0
            int rootY = find(y);//0     count++  1      count++     1           1
            if (rootX == rootY) {    // count--         count--             count--
                return;
            }

            parent.put(rootX, rootY);//10001->1  0->1   10002->1  1->1
            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }
    }

    //并查集核心：find()  找代表      union()    合并

    /**
     * int find(int x){
     *     if(a[x] = x){//是自己的老大
     *         return x;
     *     } else {
     *         return find(a[x]);//有老大，去找老大吧
     *     }
     * }
     */

    /**
     * void union(int i, int j){//合并了
     *      a[find(i)] = find(j);   //i 的老大  认 j 的老大  当老大了（以后j的小弟找老大的时候，都会找到i的老大）
     * }
     */

    /**
     * 如果每次只记自己的老大，比较的顺序特定的时候，老大链会很长，这个时候，需要路径压缩
     *
     * int find(int x){
     *     if(a[x] = x){//是自己的老大
     *         return x;
     *     } else {
     *          a[x] = find(a[x]);先找到最大的老大，直接认最大的老大为老大，下次再找我，直接能省过中间的老大
     *         return find(a[x]);//有老大，去找老大吧
     *     }
     * }
     */

    /**
     * 简写：
     * int find(int x)
     * {
     *     return x == fa[x] ? x : (fa[x] = find(fa[x]));
     * }
     */

    /**
     * 为了让树的深度减少，合并的规则：把简单的树往复杂的树上合并
     */
//    https://zhuanlan.zhihu.com/p/93647900/


}
