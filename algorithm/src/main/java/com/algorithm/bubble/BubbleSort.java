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
        // 普通冒泡排序
        System.out.println("普通冒泡排序");
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 0; i < arr.length - 1; i++) {
                        for (int j = 0; j < arr.length - i - 1; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                            }
                        }
                    }
                });

        // 布尔类型变量判断优化冒泡排序
        System.out.println("布尔类型变量判断优化冒泡排序");
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 0; i < arr.length - 1; i++) {
                        boolean isSorted = true;
                        for (int j = 0; j < arr.length - i - 1; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                                isSorted = false;
                            }
                        }
                        if (isSorted) {
                            break;
                        }
                    }
                });

        // 无序边界值优化冒泡排序
        System.out.println("无序边界值优化冒泡排序");
        SortRunner.run(SortUtils.generateAlmostOrderlyArray(10000, 100),
                (arr) -> {
                    int lastExchangeIndex = 0;
                    int sortBorder = arr.length - 1;
                    for (int i = 0; i < arr.length - 1; i++) {
                        boolean isSorted = true;
                        for (int j = 0; j < sortBorder; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                                isSorted = false;
                                lastExchangeIndex = j;
                            }
                        }
                        sortBorder = lastExchangeIndex;
                        if (isSorted) {
                            break;
                        }
                    }
                });

        // 鸡尾酒排序
        System.out.println("鸡尾酒排序");
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    for (int i = 0; i < arr.length / 2; i++) {
                        // 奇数轮，从左往右排序
                        boolean isSorted = true;
                        for (int j = i; j < arr.length - i - 1; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                                isSorted = false;
                            }
                        }
                        if (isSorted) {
                            break;
                        }

                        // 偶数轮，从右往左毕竟
                        isSorted = true;
                        for (int j = arr.length - i - 1; j > i; j--) {
                            if (arr[j] < arr[j - 1]) {
                                SortUtils.swap(arr, j, j - 1);
                                isSorted = false;
                            }
                        }
                        if (isSorted) {
                            break;
                        }
                    }
                });


        // 左右无序边界值优化鸡尾酒排序
        System.out.println("左右无序边界值优化鸡尾酒排序");
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    int leftExchangeIndex = 0;
                    int leftSortBorder = leftExchangeIndex;
                    int rightExchangeIndex = arr.length - 1;
                    int rightSortBorder = rightExchangeIndex;
                    for (int i = 0; i < arr.length / 2; i++) {
                        // 奇数轮，从左往右排序
                        boolean isSorted = true;
                        for (int j = i; j < rightSortBorder; j++) {
                            if (arr[j] > arr[j + 1]) {
                                SortUtils.swap(arr, j, j + 1);
                                isSorted = false;
                                rightExchangeIndex = j;
                            }
                        }
                        rightSortBorder = rightExchangeIndex;
                        if (isSorted) {
                            break;
                        }

                        // 偶数轮，从右往左毕竟
                        isSorted = true;
                        for (int j = arr.length - i - 1; j > leftSortBorder; j--) {
                            if (arr[j] < arr[j - 1]) {
                                SortUtils.swap(arr, j, j - 1);
                                isSorted = false;
                                leftExchangeIndex = j;
                            }
                        }
                        leftSortBorder = leftExchangeIndex;
                        if (isSorted) {
                            break;
                        }
                    }
                });

    }

}
