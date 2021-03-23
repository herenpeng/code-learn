package com.algorithm;

import com.algorithm.util.SortUtils;

/**
 * @author herenpeng
 * @since 2021-03-22 23:26
 */
public class SortRunner {

    public static void run(int[] arr, Sort sort) {
        System.out.print("数组排序前：");
        SortUtils.printArray(arr);
        long startTime = System.currentTimeMillis();
        sort.sort(arr);
        long endTime = System.currentTimeMillis();
        if (SortUtils.isOrderly(arr)) {
            System.out.print("数组排序后：");
            SortUtils.printArray(arr);
            System.out.println("数组排序所需时间为：" + (endTime - startTime) + "毫秒");
            return;
        }
        System.out.println("数组排序失败");
    }

}