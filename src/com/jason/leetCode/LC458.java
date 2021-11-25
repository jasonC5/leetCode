package com.jason.leetCode;

/**
 * buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 * <p>
 * 喂猪的规则如下：
 * <p>
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/poor-pigs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC458 {
    public int poorPigs0(int buckets, int minutesToDie, int minutesToTest) {
        int m = minutesToTest / minutesToDie;//一共可以测几轮
        // 思路 ： 假设n只猪，经过m轮一共能检测多少水？
        // 1.n只猪，一轮能检测多少桶水？ 每个猪只有两种结果：die live，无论怎么设计实验，信息量只能提供：2^n 这么多
        // 2.m轮：对每一只猪而言，经过m轮，有可能的结果为：第一轮die，第二轮dai……第m轮die，m轮都没die，最多能代表（m+1）种情况，能提供m+1的信息量
        // 3.结合以上两点，n只猪，经过m轮，最多能提供的信息为：(m+1)^n【这里并不知道怎么设计实验，只是能代表这么多信息量】，假设结果为p即：p = (m+1)^n
        // 4.现已知p和m，求n， n = log (m+1)为底 p的对数 = log p/ log (m+1)，且猪为整数，向上取整
        // 理论支持：《信息论》
        return (int) Math.ceil(Math.log(buckets) / Math.log(m + 1));
    }

//    动态规划
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int m = minutesToTest / minutesToDie;//一共可以测几轮
        // 从1开始算，i只小猪，在m轮下，能检测多少桶
        return 0;
    }

}
