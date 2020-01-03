package stack;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/3 17:33
 * @Version 1.0
 */
public class RecurdionTestDemo {
    public static void main(String[] args){
        getValue(4);
        System.out.println(factor(5));
    }
    /**
     * Example1：
     */
    public static  void getValue(int value){
        if(value>2){
            getValue(value-1);
        }
        System.out.println("the value is "+value);

    }
    /**
     * Example:阶乘
     */
    public static int factor(int n){
        if(n==1){
            return 1;
        }
        else {
            return factor(n-1)*n;
        }

    }

}
