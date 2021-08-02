package com.jason.leetCode;

import java.util.*;

/**
 * 有 n 个网络节点，标记为1到 n。
 * <p>
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC743 {
    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}}; int n = 4, k = 2;
        int time = networkDelayTime(times, n, k);
        System.out.println(time);
    }


    public static class Node<T> {
        T value;
        //相邻点（子节点）
        public List<Node<T>> nexts;
        //子节点边
        public List<Edge> edges;

        public Node(T value) {
            this.value = value;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    static class Edge {
        //权重
        public int weight;
        //源节点
        public Node source;
        //目标节点
        public Node target;

        public Edge(int weight, Node source, Node target) {
            this.weight = weight;
            this.source = source;
            this.target = target;
        }
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, Node<Integer>> indexMap = new HashMap<>();
        Node<Integer> from = toMap(times, n, k, indexMap);
        HashMap<Node<Integer>, Integer> map = dijkstra1(from);
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            if (!map.containsKey(indexMap.get(i)) || map.get(indexMap.get(i)).equals(Integer.MAX_VALUE)) {
                return -1;
            }
            ans = Math.max(ans, map.get(indexMap.get(i)));
        }
        return ans;
    }

    private static Node<Integer> toMap(int[][] times, int n, int k, HashMap<Integer, Node<Integer>> indexMap) {
        Node<Integer> kNode = null;
        for (int i = 1; i <= n; i++) {
            Node<Integer> node = new Node<>(i);
            indexMap.put(i, node);
            if (i == k) {
                kNode = node;
            }
        }
        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];
            int sourceVal = time[0];
            int targetVal = time[1];
            int height = time[2];
            Node<Integer> sourceNode = indexMap.get(sourceVal);
            Node<Integer> targetNode = indexMap.get(targetVal);
            Edge edge = new Edge(height, sourceNode, targetNode);
            sourceNode.edges.add(edge);
            sourceNode.nexts.add(targetNode);
        }
        return kNode;
    }

    public static HashMap<Node<Integer>, Integer> dijkstra1(Node<Integer> from) {
        HashMap<Node<Integer>, Integer> ans = new HashMap<>();
        ans.put(from, 0);
        Set<Node<Integer>> seen = new HashSet<Node<Integer>>();
        Node<Integer> minNode = getMinDistanceAndUnselectedNode(ans, seen);
        while (minNode != null) {
            //这个点到from的距离
            int distance = ans.get(minNode);
            for (Edge edge : minNode.edges) {
                int targetDistance = distance + edge.weight;
                //如果连接点未扫过，直接放入ans
                if (!ans.containsKey(edge.target)) {
                    ans.put(edge.target, targetDistance);
                } else {
                    //对比已经存在的点,若比当前小，则替换
                    ans.put(edge.target, Math.min(ans.get(edge.target), targetDistance));
                }
            }
            seen.add(minNode);
            //继续查找下一个点
            minNode = getMinDistanceAndUnselectedNode(ans, seen);
        }
        //从已经解锁的点中，找出离from最近的
        return ans;
    }

    private static Node<Integer> getMinDistanceAndUnselectedNode(HashMap<Node<Integer>, Integer> ans, Set<Node<Integer>> seen) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node<Integer>, Integer> entry : ans.entrySet()) {
            Node<Integer> node = entry.getKey();
            Integer distance = entry.getValue();
            if (seen.contains(node)) {
                continue;
            }
            if (distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
