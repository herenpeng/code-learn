package com.algorithm.insert;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

/**
 * @author herenpeng
 * @since 2021-03-27 20:05
 */
public class InsertSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 1; i < arr.length; i++) {
                        int insertValue = arr[i];
                        int j;
                        for (j = i; j > 0 && arr[j - 1] > insertValue; j--) {
                            arr[j] = arr[j - 1];
                        }
                        arr[j] = insertValue;
                    }
                });
    }

}
