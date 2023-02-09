package com.jason.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/2/9 9:38:47
 * @description
 */
public class LC1797 {

    class AuthenticationManager {

        int timeToLive;
        Map<String, Integer> map;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            map = new HashMap<>();
        }

        public void generate(String tokenId, int currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }

        public void renew(String tokenId, int currentTime) {
            if (map.getOrDefault(tokenId, -1) > currentTime) {
                map.put(tokenId, currentTime + timeToLive);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            int ans = 0;
            for (Integer value : map.values()) {
                ans += value > currentTime ? 1 : 0;
            }
            return ans;
        }
    }

}
