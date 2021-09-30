package com.jason.leetCode;

/**
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * <p>
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * <p>
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * 示例 2：
 * <p>
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 *  
 * <p>
 * 提示：
 * <p>
 * -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC223 {

    public static int computeArea1(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area = (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1);
        int publicArea = 0;
        //x轴有相交部分，且y轴有相交部分,算公共面积
        if (((ax1 <= bx1 && bx1 < ax2) || (bx1 <= ax1 && ax1 < bx2))
                && ((ay1 <= by1 && by1 < ay2) || (by1 <= ay1 && ay1 < by2))) {
            int x1 = Math.max(ax1, bx1);
            int x2 = Math.min(ax2, bx2);
            int y1 = Math.max(ay1, by1);
            int y2 = Math.min(ay2, by2);
            publicArea = (x2 - x1) * (y2 - y1);
        }
        return area - publicArea;
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1)) * Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
    }

    public static void main(String[] args) {
        int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;
//        int ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2;
        System.out.println(computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2));
    }

}
