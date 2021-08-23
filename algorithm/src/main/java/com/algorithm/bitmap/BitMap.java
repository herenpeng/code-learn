package com.algorithm.bitmap;

/**
 * @author herenpeng
 * @since 2021-08-23 21:09
 */
public class BitMap {

    private static int login = 0;

    public static void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > 30) {
            throw new IndexOutOfBoundsException("超过 int 类型 bit 位的有效范围");
        }
        login |= 1 << bitIndex;
    }

    public static boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > 30) {
            throw new IndexOutOfBoundsException("超过 int 类型 bit 位的有效范围");
        }
        return (login & 1 << bitIndex) != 0;
    }

    public static void main(String[] args) {
        setBit(0);
        setBit(3);
        setBit(4);
        setBit(16);
        setBit(30);
        System.out.println(Integer.toBinaryString(login));  // 1000000 00000001 00000000 00011001
        System.out.println(getBit(0));  // true
        System.out.println(getBit(1));  // false
        System.out.println(getBit(2));  // false
        System.out.println(getBit(3));  // true
        System.out.println(getBit(30));  // true
    }

}
