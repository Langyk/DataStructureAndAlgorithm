package sortAlgorithm;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/5 20:37
 * @Version 1.0
 */
public class InsertSort {
    public static void main(String[] args){
        int[] arr={101,34,119,1,-2};
        System.out.println("排序前~~~");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序后~~~~");
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int[] arr){
        int insertScrpt=0;//要插入的位置
        int insertValue=0;//插入的值
        for(int i=1;i<=arr.length-1;i++){
            insertScrpt=i-1;
            insertValue=arr[i];
            while (insertScrpt>=0&&insertValue<arr[insertScrpt]){
                arr[insertScrpt+1]=arr[insertScrpt];//向后移
                insertScrpt--;
            }
            if(insertScrpt+1!=i){
                arr[insertScrpt+1]=insertValue;
            }
        }
    }
}
