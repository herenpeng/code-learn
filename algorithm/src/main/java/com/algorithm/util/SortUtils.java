package com.algorithm.util;

/**
 * 排序工具类
 *
 * @author herenpeng
 * @since 2021-03-22 23:19
 */
public class SortUtils {

    /**
     * 随机生成一个整形数组
     *
     * @param size   数组大小
     * @param rangeL 数组左边范围（包含）
     * @param rangeR 数组右边范围（包含）
     * @return 一个整形数组
     */
    public static int[] generateRandomArray(int size, int rangeL, int rangeR) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1)) + rangeL;
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的整形数组
     *
     * @param size      数组大小
     * @param swapTimes 将有序数组打乱次数
     * @return 一个近乎有序的整形数组
     */
    public static int[] generateAlmostOrderlyArray(int size, int swapTimes) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < swapTimes; i++) {
            int index1 = (int) (Math.random() * size);
            int index2 = (int) (Math.random() * size);
            swap(arr, index1, index2);
        }
        return arr;
    }

    /**
     * 判断一个数组是否有序，有序返回true，无序返回false
     *
     * @param arr 数组
     * @return 是否有序
     */
    public static boolean isOrderly(int[] arr) {
        return isOrderAsc(arr) || isOrderDesc(arr);
    }

    /**
     * 判断一个数组是否顺序有序，有序返回true，无序返回false
     *
     * @param arr 数组
     * @return 是否有序
     */
    private static boolean isOrderAsc(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个数组是否倒序有序，有序返回true，无序返回false
     *
     * @param arr 数组
     * @return 是否有序
     */
    private static boolean isOrderDesc(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 拷贝一个整形数组
     *
     * @param arr 整形数组
     * @return 新的整形数组
     */
    public static int[] copyIntArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    /**
     * 交换数组中的两个索引的位置
     *
     * @param arr 整形数组
     * @param i   第一个索引
     * @param j   第二个索引
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     *
     * @param arr 整形数组
     */
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

}
