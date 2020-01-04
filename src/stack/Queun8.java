package stack;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/4 10:59
 * @Version 1.0
 * <p>
 * 问题描述：
 * 在一个8*8的国际象棋上摆放八个皇后，使其不能相互攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，共有多少种摆法？
 */

/**
 * 问题描述：
 * 在一个8*8的国际象棋上摆放八个皇后，使其不能相互攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，共有多少种摆法？
 */

/**
 * 问题分析
 * 1、第一个皇后放在第一行第一列
 * 2、第二个皇后放在第二行第一列，然后判断是否Ok，如果不Ok，继续放在第二列、第三列，依次把所有列都放完，找到一个合适的
 * 3、继续放第三个皇后，继续放第一列、第二列，.......直到第8个皇后放在一个不冲突的位置，这就算找到了一个正确解
 * 4、当找到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一皇后放到第一列的所有正确解，全部得到
 * 5、然后回头继续第一个皇后放第二列，后面继续执行1,2,3,4步骤
 * 说明：
 *     使用一个1维数组来表示，arr[8]={0,4,7,5,2,6,1,3}，下标对应第几行，arr[i]=value,value表示第i+1个皇后放在第i+1列。
 */
public class Queun8 {
    int maxSize=8;
    int arr[]=new int[maxSize];
    int count=0;
    public static void main(String[] args) {
    Queun8 queun8=new Queun8();
    queun8.check(0);
    System.out.println("the count is "+queun8.count);
    }


    /**
     * 放置皇后
     */
    private void check(int n){
        //8个皇后已全部放置完毕
        if (n==maxSize){
            print();
            return;
        }
        for (int i=0;i<maxSize;i++){
            arr[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }
    }

    /**
     * 判断皇后的位置是否冲突
     */
    private boolean judge(int n){
        for(int i=0;i<n;i++){
            //不在同一列、不在同一斜线上
            if(arr[i]==arr[n]||Math.abs(n-i)==Math.abs((arr[n])-Math.abs(arr[i]))){
                return false;
            }
        }
        return true;
    }
    /**
 * 打印每一组解
 */
private  void print(){
    count++;
    for (int i=0;i<arr.length;i++){
   System.out.print(arr[i]+"");
    }
    System.out.print("\n");
}
}
