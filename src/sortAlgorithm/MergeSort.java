package sortAlgorithm;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/29 17:18
 * @Version 1.0
 */

import java.util.Arrays;

/**
 * 描述：归并排序是将一个数组的不断的拆分，可以描述为1个数组拆分成左右2个数组，在分别对这两个数组拆分成更小的左右两个数组
 * ，一直重复，直至无法拆分。如满二叉树
 * 合并：分别比较对拆分的数组的分别比较大小合并，如对满二叉树的层比较大小进行合并。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 分解+合并
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   原始数组，待排序的数组
     * @param left  数组的左边坐标
     * @param mid   数组的中间坐标
     * @param right 数组的右边坐标
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int temp[]) {
        int i = left;//初始化i，左边序列的初始索引
        int j = mid + 1;//初始化j，右边序列的初始索引
        int t = 0; //临时变量的索引
        //1、将拆开的左右数组中的元素分别比较大小，并放入临时数组，直至某一数组被放完
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //2、将剩下数组的元素全部移动到临时数组中
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }
        //3 将临时数组的元素合更新到原数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}
