package com.algorithm.bubble;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

/**
 * @author herenpeng
 * @since 2021-03-26 22:35
 */
public class BubbleSortTest {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateAlmostOrderlyArray(100000, 100),
                (arr) -> {
                    // 左边最后进行元素交换的索引位置
                    int leftExchangeIndex = 0;
                    // 左边界
                    int leftSortBorder = leftExchangeIndex;
                    // 右边最后进行元素交换的索引位置
                    int rightExchangeIndex = arr.length - 1;
                    // 右边界
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
