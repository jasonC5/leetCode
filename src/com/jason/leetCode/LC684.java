package com.jason.leetCode;

public class LC684 {
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            int pointCount = edges.length;
            int[] minPointArr = new int[pointCount+1];//数组的位置代表对应节点，数组中的值代表当前可到达的最小节点
            for(int i = 1 ; i< pointCount+1 ;i++){
                minPointArr[i] = i;//初始化成都只能达到自己
            }
            for(int i = 0;i < edges.length;i++){
                int[] edge = edges[i];
                int point1 = edge[0];
                int point2 = edge[1];
                if(findMin(minPointArr, point1) == findMin(minPointArr, point2)){//这两点之前就是可到达的
                    return edge;
                } else {
                    //不可到达，所以看两个点现在谁都能到的最小，都赋值成最小的
                    if(findMin(minPointArr, point1) < findMin(minPointArr, point2)){
                        // minPointArr[point1] = findMin(minPointArr, point1);
                        // minPointArr[point2] = findMin(minPointArr, point1);//都附上，减少后续复杂度
                        update(minPointArr, point1, findMin(minPointArr, point1));
                        update(minPointArr, point2, findMin(minPointArr, point1));
                    } else {
                        // minPointArr[point1] = findMin(minPointArr, point2);
                        // minPointArr[point2] = findMin(minPointArr, point2);//都附上，减少后续复杂度
                        update(minPointArr, point1, findMin(minPointArr, point2));
                        update(minPointArr, point2, findMin(minPointArr, point2));
                    }
                }
            }
            return null;
        }

        public void update(int[] arr, int idx, int min){
            if(arr[idx] == idx){
                arr[idx] = min;
            } else{
                int oldMin = arr[idx];
                arr[idx] = min;
                update(arr, oldMin, min);
            }
        }

        //查找指定节点所能到达的最小位置（若两个节点都能到达同一个最小节点的位置，岂不是两个节点是联通的）
        public int findMin(int[] arr, int idx){
            if(arr[idx] == idx){//能到达最小的就是自己，直接return
                return idx;
            } else {
                return findMin(arr, arr[idx]);//找到上一次自己能到达的最小位置，看是否还需要往下走
            }
        }
    }
}
