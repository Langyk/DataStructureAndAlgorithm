package sortAlgorithm;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/9 20:20
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr={-9,78,0,23,-567,70};
        int left=0;
        int right=arr.length-1;
        quickSort(arr,left,right);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int temp=0;
        int privot=arr[(right+left)/2];
       while (l<r){
           while (arr[l]<privot){
                l++;
           }
           while (arr[r]>privot){
               r--;
           }
           if(l>=r){
               break;
           }
           temp=arr[l];
           arr[l]=arr[r];
           arr[r]=temp;

           if(arr[l]==privot){
               r--;
           }
           if(arr[r]==privot){
               l++;
           }
       }
       if(l==r){
           l+=1;
           r-=1;
       }
       //左递归
        if (left<r){
            quickSort(arr,left,r);

        }
        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
