package sortAlgorithm;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/5 19:25
 * @Version 1.0
 */
public class SelectedSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        System.out.println("排序前~~~");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序后~~~~");
        sort(arr);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minSize = i;
            int min = arr[minSize];
            for (int j = i + 1; j <= arr.length - 1; j++) {
                if(min>arr[j]){
                    minSize=j;
                    min=arr[j];
                }
            }
            if(i!=minSize){
                arr[minSize]=arr[i];
                arr[i]=min;

            }
            System.out.println(Arrays.toString(arr));

        }
    }
}
