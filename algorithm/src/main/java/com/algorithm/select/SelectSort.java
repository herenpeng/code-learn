package com.algorithm.select;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

/**
 * @author herenpeng
 * @since 2021-03-27 20:00
 */
public class SelectSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 0; i < arr.length - 1; i++) {
                        int mixIndex = i;
                        // 下面的循环从i+1开始，因为i之前的索引位置的元素都是有序的
                        for (int j = i + 1; j < arr.length; j++) {
                            if (arr[j] < arr[mixIndex]) {
                                mixIndex = j;
                            }
                        }
                        SortUtils.swap(arr, i, mixIndex);
                    }
                });
    }

}
