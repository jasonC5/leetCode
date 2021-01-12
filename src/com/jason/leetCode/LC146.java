package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU算法
 */
public class LC146 {
    //实现方式：HashMap+双向链表
//    public static void main(String[] args) {
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        lRUCache.get(1);    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        lRUCache.get(2);    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        lRUCache.get(1);    // 返回 -1 (未找到)
//        lRUCache.get(3);    // 返回 3
//        lRUCache.get(4);    // 返回 4
//    }
    class LRUCache {

        //实现方式：HashMap+双向链表

        class Node {
            Node next;//下一个
            Node prov;//上一个
            int key;
            int value;
            public Node(){}

            public Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        Map<Integer , Node> nodeMap = new HashMap();
        Node head;//链表头部
        Node tail;//链表尾部
        int capacity;//容量
        int size;//大小

        public LRUCache(int capacity) {
            this.head = new Node();
            this.tail = new Node();
            this.capacity = capacity;
            this.size = 0;
            head.next = tail;//头尾互指，链表为空
            tail.prov = head;
        }

        public int get(int key) {
            //通过HashMap查找到NodeX，并将该Node挪到第一个
            Node node = nodeMap.get(key);
            if(node == null){
                return -1;
            } else {
                movNode2Top(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            //若key已存在
            //获取到Node，修改value，并将Node放到第一个位置
            //若key不存在
            //若链表未满
            //新增Node，并将该Node放到第一个位置
            //若链表已满 size == capacity
            //1.将尾部Node删除
            //2.生成一个新的Node
            //3.挂到头部
            Node node = nodeMap.get(key);
            if(node != null){
                movNode2Top(node);
                node.value = value;
            } else {
                node = new Node(key, value);
                if(capacity > size){ //还有空间放，直接放
                    putNode2Top(node);
                    nodeMap.put(key, node);
                    size++;
                } else {
                    //没有空间了，需要先删除，再放
                    Node bottom = tail.prov;
                    nodeMap.remove(bottom.key);
                    removeNode(bottom);
                    bottom = null;
                    nodeMap.put(key, node);
                    putNode2Top(node);
                }
            }
        }
        //挂载一个node到头部
        public void putNode2Top(Node node){
            Node oldTop = head.next;//旧头部
            head.next = node;//成为新的头部
            node.prov = head;
            node.next = oldTop;//头部的下一个，挂上旧的头部
            oldTop.prov = node;//旧头部的上一个，挂载新的头部
        }
        //删除一个Node
        public void removeNode(Node node){
            Node nodeNext = node.next;
            Node nodeProv = node.prov;
            nodeNext.prov = nodeProv;
            nodeProv.next = nodeNext;
            // node = null; 不能在这删，移动的时候会有问题（也不影响，形参实参……）
        }
        //移动一个Node到头部（删除+挂载头部）
        public void movNode2Top(Node node){
            removeNode(node);//先从原有位置删除
            putNode2Top(node);//再挂到头部
        }

    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}
