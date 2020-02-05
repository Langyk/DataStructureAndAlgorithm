package tree;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/5 14:12
 * @Version 1.0
 */
public class HeapSort {
    public static void main(String[] args){
        int[] arr={4,6,8,5,9,-44,11,98,3};

        //将无序序列构建成一个堆，根据升序或者降序的需求构造大顶堆或者小顶堆
        //arr.length-1/2是为了找到最后一个非叶子结点
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);

        }
        //将堆顶元素与末尾元素交换，将最大的元素放置在数组的尾端
        //重新调整以满足堆的结构，继续交换堆顶元素与数组末尾元素，直到整个序列有序
        for(int j=arr.length-1;j>0;j--){
            int temp;
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr 数组（二叉树）
     * @param i 传进的非叶子结点坐标
     * @param length 数组的长度
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i];
        for(int k=i*2+1;k<length;k=k*2+1){
            //找到最大的子结点
            if(k+1<length&&arr[k]<arr[k+1]){
                k++;
            }
            if(temp<arr[k]){
                arr[i]=arr[k];
                i=k;//将i等于非叶子结点的左子结点坐标
            }
            //将非叶子结点的值赋给与它交换的子结点
            arr[i]=temp;
        }
    }
}
