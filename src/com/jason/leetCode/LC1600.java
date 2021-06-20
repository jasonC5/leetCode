package com.jason.leetCode;

import java.util.*;

/**
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 *
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数Successor(x, curOrder)，给定一个人x和当前的继承顺序，该函数返回 x的下一继承人。
 *
 * Successor(x, curOrder):
 *     如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
 *         如果 x 是国王，那么返回 null
 *         否则，返回 Successor(x 的父亲, curOrder)
 *     否则，返回 x 不在 curOrder 中最年长的孩子
 * 比方说，假设王国由国王，他的孩子Alice 和 Bob （Alice 比 Bob年长）和 Alice 的孩子Jack 组成。
 *
 * 一开始，curOrder为["king"].
 * 调用Successor(king, curOrder)，返回 Alice ，所以我们将 Alice 放入 curOrder中，得到["king", "Alice"]。
 * 调用Successor(Alice, curOrder)，返回 Jack ，所以我们将 Jack 放入curOrder中，得到["king", "Alice", "Jack"]。
 * 调用Successor(Jack, curOrder)，返回 Bob ，所以我们将 Bob 放入curOrder中，得到["king", "Alice", "Jack", "Bob"]。
 * 调用Successor(Bob, curOrder)，返回null。最终得到继承顺序为["king", "Alice", "Jack", "Bob"]。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
 *
 * 请你实现ThroneInheritance类：
 *
 * ThroneInheritance(string kingName) 初始化一个ThroneInheritance类的对象。国王的名字作为构造函数的参数传入。
 * void birth(string parentName, string childName)表示parentName新拥有了一个名为childName的孩子。
 * void death(string name)表示名为name的人死亡。一个人的死亡不会影响Successor函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
 * string[] getInheritanceOrder()返回 除去死亡人员的当前继承顺序列表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/throne-inheritance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1600 {


    class ThroneInheritance {
        private String kingName;
        private Set<String> dead;
        private Map<String, List<String>> sons;

        public ThroneInheritance(String kingName) {
            this.kingName = kingName;
            this.dead = new HashSet<>();
            this.sons = new HashMap<>();
        }
        //表示 parentName 新拥有了一个名为 childName 的孩子。
        public void birth(String parentName, String childName) {
            List<String> thisSon = this.sons.getOrDefault(parentName, new ArrayList<>());
            thisSon.add(childName);
            this.sons.put(parentName, thisSon);
        }
        //表示名为 name 的人死亡。一个人的死亡不会影响 Successor 函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
        public void death(String name) {
            dead.add(name);
        }
        //返回 除去 死亡人员的当前继承顺序列表。
        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            //先序遍历
            process(kingName, ans);
            return ans;
        }

        private void process(String name, List<String> ans) {
            if (!dead.contains(name)) {
                ans.add(name);
            }
            List<String> sons = this.sons.getOrDefault(name, new ArrayList<>());
            for (String son : sons) {
                process(son, ans);
            }
        }
    }


}
