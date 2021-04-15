package com.algorithm.quick;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

/**
 * @author herenpeng
 * @since 2021-04-14 21:53
 */
public class QuickSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    quickSort(arr, 0, arr.length - 1);
                });
    }


    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        // int pivotIndex = partition(arr, startIndex, endIndex);
        int pivotIndex = partition1(arr, startIndex, endIndex);
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 获取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                SortUtils.swap(arr, left, right);
            }
        }
        SortUtils.swap(arr, startIndex, left);
        return left;
    }


    private static int partition1(int[] arr, int startIndex, int endIndex) {
        // 获取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                SortUtils.swap(arr, mark, i);
            }
        }
        SortUtils.swap(arr, startIndex, mark);
        return mark;
    }


}
