package tenalgorithm;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/11 20:25
 * @Version 1.0
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args){
        int[] arr={1,3,8,10,11,67,100};
        int index=binarySearch(arr,200);
        System.out.println(index);
    }
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]<target){
                left=mid+1;
            }else if (arr[mid]>target){
                right=mid-1;
            }
        }
        return -1;
    }
}
