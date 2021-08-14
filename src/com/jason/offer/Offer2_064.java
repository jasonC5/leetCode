package com.jason.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer2_064 {
    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        String[] arr = {"hello", "leetcode"};
        magicDictionary.buildDict(arr);
        magicDictionary.search("hhllo");
    }

    public static class MagicDictionary2 {
        Map<Integer, List<String>> dir;
        /** Initialize your data structure here. */
        public MagicDictionary2() {
            dir = new HashMap<>();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                List<String> stringList = dir.getOrDefault(str.length(), new ArrayList<>());
                stringList.add(str);
                dir.put(str.length(), stringList);
            }
        }

        public boolean search(String searchWord) {
            List<String> list = dir.get(searchWord.length());
            if (list == null){
                return false;
            }
            for (String str : list) {
                int counter = 0;
                for (int i = 0; i < searchWord.length(); i++) {
                    if (searchWord.charAt(i) != str.charAt(i)) {
                        counter++;
                    }
                    if (counter > 1) {
                        break;
                    }
                }
                if (counter == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class MagicDictionary {
        String[] dir;
        /** Initialize your data structure here. */
        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {
            dir = dictionary;
        }

        public boolean search(String searchWord) {
            for (String str : dir) {
                if (searchWord.length() != str.length()){
                    continue;
                }
                //长度相等了
                int counter = 0;
                for (int i = 0; i < searchWord.length(); i++) {
                    if (searchWord.charAt(i) != str.charAt(i)) {
                        counter++;
                    }
                    if (counter > 1) {
                        break;
                    }
                }
                if (counter == 1) {
                    return true;
                }
            }
            return false;
        }
    }


}
