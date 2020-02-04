package search;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/31 11:36
 * @Version 1.0
 */

/**
 * 插值查找算法描述：
 * 改进了查找中间值的算法公式：
 * mid=left+(right-low)*(findValue-arr[left])/(arr[right]-arr[left])
 */
public class InsertValueSearch {
    public static void main(String[] args){
        int[] arr=new int[100];
        for (int i=0;i<100;i++){
            arr[i]=i;
        }
        int index=insertValueSearch(arr,0,arr.length-1,55);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr,int left,int right,int findValue){
        if(left>right||findValue<arr[left]||findValue>arr[right]){
            return -1;
        }
        int mid=left+(right-left)*(findValue-arr[left])/(arr[right]-arr[left]);
        if(findValue>arr[mid]){
            //向右递归
           return insertValueSearch(arr,mid+1,right,findValue);
        }
      else if(findValue<arr[mid]){
            //向左递归
           return insertValueSearch(arr,left,mid-1,findValue);
        }
      else {
          return mid;
        }
    }

}
