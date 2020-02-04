package search;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/30 17:55
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 二分差查找时，数组必须是有序的
 * 1、普通二分查找
 * 2、将序列中的指定元素都查找出来
 * {1,8,10,8,9,1000,，1000,1000，1234}，将序列中的元素10000都查找出来
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 8, 9, 1000, 1234};
//       int index= binarySearch(arr,0,arr.length-1,22);
//       System.out.println(index);

        int[] arr2 = {1, 8, 10, 8, 9, 1000, 1000, 1000, 1234};
        ArrayList<Integer> list = binarySearch2(arr2, 0, arr.length - 1, 1000);
        System.out.println(list);
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (right + left) / 2;
        if (left > right) {
            return -1;
        }
        if (value > arr[mid]) {
            //向右递归
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            //向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找的代码优化，可以对序列中的每一个重复元素查找出下标
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        int mid = (right + left) / 2;
        if (left > right) {
            return new ArrayList<>();
        }
        if (value > arr[mid]) {
            //向右递归
            return binarySearch2(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            //向左递归
            return binarySearch2(arr, left, mid - 1, value);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid-1;
            //向左索引
            while (true) {
                if (temp< 0 ||arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            //向右索引
            temp=mid+1;
            while (true) {
                if (temp > arr.length - 1 ||arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            list.add(mid);
            return list;
        }
    }
}
