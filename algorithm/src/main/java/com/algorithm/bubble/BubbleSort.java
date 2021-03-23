package com.algorithm.bubble;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

/**
 * 冒泡排序
 *
 * @author herenpeng
 * @since 2021-03-22 23:22
 */
public class BubbleSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 0; i < arr.length - 1; i++) {
                        for (int j = 0; j < arr.length - 1; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                            }

                        }
                    }
                });

    }

}
