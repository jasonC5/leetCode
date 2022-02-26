package com.jason.leetCode;

public class LC2166 {
    public static class Bitset {
        long[] bitmap;
        boolean flip;
        int oneCount;
        int size;

        public Bitset(int size) {
            int segment = (size + 63) / 64;
            this.bitmap = new long[segment];
            this.flip = false;
            this.oneCount = 0;
            this.size = size;
        }

        public void fix(int idx) {
            int segment = idx / 64;
            int offset = idx % 64;
            long mask = 1L << offset;
            long bit = bitmap[segment] & mask;
            if (bit == 0 && !flip) {
                oneCount++;
                bitmap[segment] |= mask;
            } else if (bit != 0 && flip) {
                oneCount++;
                bitmap[segment] &= ~mask;
            }
        }

        public void unfix(int idx) {
            int segment = idx / 64;
            int offset = idx % 64;
            long mask = 1L << offset;
            long bit = bitmap[segment] & mask;
            if (bit != 0 && !flip) {//没翻转，而且上面是1
                oneCount--;
                bitmap[segment] &= ~mask;//清0
            } else if (bit == 0 && flip) {//翻转了，上面是0--实际是1
                oneCount--;
                bitmap[segment] |= mask;//变成1--实际是0
            }
        }

        public void flip() {
            flip = !flip;
            oneCount = size - oneCount;
        }

        public boolean all() {
            return oneCount == size;
        }

        public boolean one() {
            return oneCount > 0;
        }

        public int count() {
            return oneCount;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int segment = i / 64;
                int offset = i % 64;
                long bit = bitmap[segment] >> offset & 1;
                sb.append(flip ? (1 - bit) : bit);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Bitset bitset = new Bitset(10);
        bitset.fix(3);
        bitset.fix(1);
        System.out.println(bitset.count());
        System.out.println(bitset.toString());
        bitset.flip();
        System.out.println(bitset.count());
        System.out.println(bitset.toString());
    }
}
