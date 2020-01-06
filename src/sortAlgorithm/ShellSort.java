package sortAlgorithm;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/6 19:58
 * @Version 1.0
 */

import java.util.Arrays;

/**
 * 思想：序列的两元素间确定增量，根据增量的改变达到排序的过程（为了应对过小的元素在序列后面而提出的）,
 * 希尔排序属于插入类排序,是将整个有序序列分割成若干小的子序列分别进行插入排序
 * 希尔排序有两种方法
 * 法1：交换式
 * 法2：移位式
 */
public class ShellSort {
    public static void main(String[] args) {
        System.out.println("希尔排序（交换）");
        int[] arr = {3, 5, 1, 6, 0, 8, 9, 4, 7, 2};
        exchangeShellSort(arr);
        System.out.println("希尔排序（移位）");
        int[] arr2 = {3, 5, 1, 6, 0, 8, 9, 4, 7, 2};
        shiftShellSort(arr2);

    }

    public static void exchangeShellSort(int[] arr) {
        int temp = 0;
        for (int increment = arr.length / 2; increment > 0; increment = increment / 2) {
            for (int i = increment; i < arr.length; i++) {
                for (int j = i - increment; j >= 0; j -= increment) {
                    if (arr[j] > arr[j + increment]) {
                        temp = arr[j];
                        arr[j] = arr[j + increment];
                        arr[j + increment] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }

    }

    public static void shiftShellSort(int[] arr) {
        for (int increment = arr.length / 2; increment > 0; increment = increment / 2) {

            for (int i = increment; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - increment]) {
                    while (j - increment >= 0 && temp < arr[j - increment]) {
                        arr[j] = arr[j - increment];
                        j -= increment;
                    }
                    arr[j] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }


}
