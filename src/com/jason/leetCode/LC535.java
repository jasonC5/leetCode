package com.jason.leetCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenjieaj
 * @date 2022/6/29 8:56:37
 * @description
 */
public class LC535 {

    public class Codec {
        Map<String, String> map = new ConcurrentHashMap<>();
        AtomicLong atomicLong = new AtomicLong(10000);

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String key = atomicLong.getAndIncrement() + "";
            map.put(key, longUrl);
            return key;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }
}
