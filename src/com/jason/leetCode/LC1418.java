package com.jason.leetCode;


import java.util.*;

/**
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * <p>
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 * <p>
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * 示例 2：
 * <p>
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 * 示例 3：
 * <p>
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1418 {

    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("David");
        list1.add("3");
        list1.add("Ceviche");
        orders.add(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("Corina");
        list2.add("10");
        list2.add("Burrito");
        orders.add(list2);
        List<String> list3 = new ArrayList<>();
        list3.add("David");
        list3.add("3");
        list3.add("Fried Chicken");
        orders.add(list3);
        List<String> list4 = new ArrayList<>();
        list4.add("Carla");
        list4.add("5");
        list4.add("Water");
        orders.add(list4);
        List<String> list5 = new ArrayList<>();
        list5.add("Carla");
        list5.add("5");
        list5.add("Ceviche");
        orders.add(list5);
        List<String> list6 = new ArrayList<>();
        list6.add("Rous");
        list6.add("3");
        list6.add("Ceviche");
        orders.add(list6);
        List<List<String>> lists = displayTable(orders);
        System.out.println(lists);
    }

    public static List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ans = new ArrayList<>();
        //每桌食物
        Map<Integer, Map<String, Integer>> displayTable = new TreeMap<>();
        //字典序排序菜单
        Set<String> foods = new TreeSet<>();
        for (List<String> order : orders) {
//            String custom = order.get(0);
            Integer tableNum = Integer.valueOf(order.get(1));
            String foodName = order.get(2);
            Map<String, Integer> tableFoods = displayTable.getOrDefault(tableNum, new HashMap<>());
            Integer foodNum = tableFoods.getOrDefault(foodName, 0);
            tableFoods.put(foodName, foodNum + 1);
            displayTable.put(tableNum, tableFoods);
            foods.add(foodName);
        }
        List<String> title = new ArrayList<>();
        title.add("Table");
        for (String food : foods) {
            title.add(food);
        }
        ans.add(title);
        for (Map.Entry<Integer, Map<String, Integer>> tableEntry : displayTable.entrySet()) {
            List<String> tableFoods = new ArrayList<>();
            tableFoods.add(tableEntry.getKey().toString());
            Map<String, Integer> foodNumMap = tableEntry.getValue();
            for (String food : foods) {
                Integer num = foodNumMap.getOrDefault(food, 0);
                tableFoods.add(num.toString());
            }
            ans.add(tableFoods);
        }
        return ans;
    }
}
