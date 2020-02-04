package search;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/30 16:27
 * @Version 1.0
 */
public class SequenceSearch {
    public static void main(String[] args){
        int[] arr={1,9,8,-44,5,6};
        int index=sequenceSearch(arr,0);
        if(index==-1){
            System.out.println("查找的值不存在");
        }else {
            System.out.println("值为："+index);
        }
    }
    public static int sequenceSearch(int[] arr,int value){
        int temp=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                temp=i;
                break;

            }else {
                temp= -1;
            }

        }
        return temp;
    }
}
