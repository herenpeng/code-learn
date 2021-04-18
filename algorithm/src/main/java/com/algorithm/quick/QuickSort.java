package com.algorithm.quick;

import com.algorithm.SortRunner;
import com.algorithm.util.SortUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 快速排序
 *
 * @author herenpeng
 * @since 2021-04-14 21:53
 */
public class QuickSort {

    public static void main(String[] args) {
        SortRunner.run(SortUtils.generateRandomArray(10000, 100, 100000),
                (arr) -> {
                    // quickSort(arr, 0, arr.length - 1);
                    quickSort1(arr, 0, arr.length - 1);
                });
    }


    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 7、根据基准元素的位置，将数组一分为二，并继续从第一步开始执行，直到所有元素有序。
        // int pivotIndex = partition1(arr, startIndex, endIndex);
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 1、选中基准元素（pivot），并将排序的数组最左边和最右边的元素索引设为 left 和 right。
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 5、从之前停止的 left 和 right 指针的位置，继续重复 2、3 步骤，直到 left 指针等于 right 指针，停止索引。
        while (left != right) {
            // 2、从 right 指针开始向左索引，如果指针指向的元素大于等于基准元素，则继续向左边移动，如果元素比基准元素小，则 right 指针停止移动。
            while (left < right && arr[right] > pivot) {
                right--;
            }
            // 3、从 left 指针开始向右索引，如果指针指向的元素小于等于基准元素，则继续向右边移动，如果元素比基准元素大，则 left 指针停止移动。
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //  4、交换 left 和 right 索引位置的元素。
            if (left < right) {
                SortUtils.swap(arr, left, right);
            }
        }
        // 6、交换 left 和 pivot 指针的元素。
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


    private static void quickSort1(int[] arr, int startIndex, int endIndex) {
        // 1、创建一个栈，将数组的开始索引和结束索引压入栈中。
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("start", startIndex);
        map.put("end", endIndex);
        stack.push(map);
        // 4、轮询栈，只要栈内元素不为空，继续执行，否则停止程序。
        while (!stack.isEmpty()) {
            // 2、取出栈顶的元素，通过数组的开始索引和结束索引，对该范围内的数组元素进行排序，获取基准元素。
            Map<String, Integer> pop = stack.pop();
            int start = pop.get("start");
            int end = pop.get("end");
            int pivotIndex = partition(arr, start, end);
            // 3、根据基准元素的位置，将数组一分为二，并将切分的数组的开始索引和结束索引压入栈中。
            if (start < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("start", start);
                leftParam.put("end", pivotIndex - 1);
                stack.push(leftParam);
            }
            if (end > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("start", pivotIndex + 1);
                rightParam.put("end", end);
                stack.push(rightParam);
            }
        }
    }


}
