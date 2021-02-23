package com.algorithm.day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author herenpeng
 * @since 2021-02-23 8:15
 */
public class ArrayTest {

    @Test
    public void test01() {
        int[] arr = new int[]{1, 5, 4, 6, 1, 3, 5, 3};
        // 输出下标为2的数组元素，即数组的第三个元素，4
        System.out.println(arr[2]);

        // 输出赋值前，下标为4的数组元素，值为：1
        System.out.println(arr[4]);
        // 赋值，改变数组元素的值
        arr[4] = 10;
        // 输出赋值后，下标为4的数组元素，值为：10
        System.out.println(arr[4]);
    }

    @Test
    public void test02() {
        // 开辟了一个数组长度为5的数组内存空间
        int[] arr = new int[5];
        arr[0] = 2;
        arr[1] = 8;
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
        // 开辟了一个数组长度为5，类型为Integer的数组内存空间
        Integer[] array = new Integer[5];
        array[0] = 2;
        array[1] = 8;
        for (Integer integer : array) {
            // 输出：2	8	null	null	null
            System.out.print(integer + "\t");
        }
    }

    @Test
    public void test03() {
        Array array = new Array(5);
        // 数组的实际元素个数为：0
        System.out.println(array.size());
        // 数组长度为：5
        System.out.println(array.length());
    }

    @Test
    public void test04() throws Exception {
        Array array = new Array(5);
        // 数组的实际元素个数为：0
        System.out.println(array.size());
        // 数组长度为：5
        System.out.println(array.length());
        System.out.println("===========");
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(3, 5);
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + "\t");
        }
        array.add(0, 45);
        array.add(4, 10);
        array.add(7, 10);
        System.out.println("===========");
        // 数组的实际元素个数为：0
        System.out.println(array.size());
        // 数组长度为：5
        System.out.println(array.length());

        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + "\t");
        }
    }


    @Test
    public void test05() throws Exception {
        Array array = new Array(5);
        // 数组的实际元素个数为：0
        System.out.println(array.size());
        // 数组长度为：5
        System.out.println(array.length());
        System.out.println("===========");
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(3, 5);


        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + "\t");
        }

        array.update(4, 15);
        System.out.println("===========");
        System.out.println(array.get(2));
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + "\t");
        }
    }

}
