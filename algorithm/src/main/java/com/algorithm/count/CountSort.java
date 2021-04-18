package com.algorithm.count;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

import java.util.Arrays;

/**
 * @author herenpeng
 * @since 2021-04-18 11:10
 */
public class CountSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 1000),
                (arr) -> {
                    // countSort(arr);
                    // countSort1(arr);
                    countSort2(arr);
                });
    }

    private static void countSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 统计每个元素的个数
        int[] countArray = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]]++;
        }
        // 通过元素个数，输入所有元素
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[index++] = i;
            }
        }
    }


    private static void countSort1(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 统计每个元素的个数
        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }
        // 通过元素个数，输入所有元素
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[index++] = i + min;
            }
        }
    }

    private static void countSort2(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 统计每个元素的个数
        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i] - min]++;
        }
        // 变形
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        int[] sortArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            // 获取排序的序号
            int sort = countArray[arr[i] - min];
            // 索引 = 序号-1
            sortArray[sort - 1] = arr[i];
            countArray[arr[i] - min]--;
        }
        System.arraycopy(sortArray, 0, arr, 0, arr.length);
    }

}
