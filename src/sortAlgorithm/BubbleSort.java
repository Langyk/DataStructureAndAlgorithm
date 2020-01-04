package sortAlgorithm;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/4 19:42
 * @Version 1.0
 */

import java.util.Arrays;

/**
 * 冒泡算法描述
 * 按某种规则顺序比较相邻元素，如果出现逆序，则交换位置
 * 时间复杂度为O（n^2）
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr1 = {3, 9, -1, 10, 20};
        int[] arr2 = {3, 9, -1, 10, 20};
        System.out.println("冒泡的基础算法~~~~~");
        basicSort(arr1);

        System.out.println("冒泡的优化算法~~~~~");
        optimizeSort(arr2);
    }

    /**
     * 基础算法
     */
    private static void basicSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            System.out.println(Arrays.toString(arr));
        }

    }

    /**
     * 优化冒泡算法
     */
    private static void optimizeSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1&&flag; i++) {
            flag=false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 交换次序的方法
     */
    private static void swap(int[] arr, int m, int n) {
        int temp = 0;
        temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }
}
