package sortAlgorithm;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/30 14:16
 * @Version 1.0
 */

import java.util.Arrays;

/**
 * 描述：基数排序就是分离出数字的每一位，根据每一位的大小放入指定的桶中，桶用数组来表示，共分成编号为0-9的桶
 * 如249，分离个位时，249放入编号为9的桶中，分离十位时，246放入编号为4的桶中
 * 分离千位时，249放入编号为0的桶中
 */
public class RadisSort {
    public static void main(String[] args){
        int[] arr={53,3,542,748,14,214};
        radisSort(arr);
    }

    public static void radisSort(int[] arr){
        //找出最大数并确定位数
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
        }
        int maxLength=(max+"").length();
        //定义桶，为了防止元素溢出，需要将每个桶的容量都定义为:arr.length
        int[][] bucket=new int[10][arr.length];
        //定义一个数组bucketElementCount[],用来记录每个桶中放入的元素的数量
        int[] bucketElementCount=new int[10];
        /**
         * 将数据元素放入桶中
         */
        for(int l=0,n=1;l<maxLength;n=n*10,l++){
            for(int i=0;i<arr.length;i++){
                int endDigital=arr[i]/n%10;
                bucket[endDigital][bucketElementCount[endDigital]]=arr[i];
                bucketElementCount[endDigital]++;
            }
            /**
             * 取出元素
             */
            int index=0;
            for(int i=0;i<bucket.length;i++){
                if(bucketElementCount[i]!=0){
                    for(int j=0;j<bucketElementCount[i];j++){
                        arr[index]=bucket[i][j];
                        index++;
                    }
                }
            //将bucketElemnetCount清零
                bucketElementCount[i]=0;
            }
            System.out.println(Arrays.toString(arr));
        }

    }
}
