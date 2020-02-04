package search;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/1 12:34
 * @Version 1.0
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 1234);
        System.out.println(index);
    }

    //构造斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契算法查找
    public static int fibonacciSearch(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        int k = 0;//记录斐波那契分隔数值的下标
        int f[] = fib();//获得斐波那契数列

        while (right > f[k] - 1) {
            k++;
        }
        //对数组a中的元素数量与斐波那契f[k]值相等
        int[] temp = Arrays.copyOf(arr, f[k]);
        //将填充的元素等于原数组的最后一个元素，进行元素补充
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = temp[right];
        }

        //斐波那契查找
        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (findValue < temp[mid]) {
                //分割点左边的数据继续进行分割
                right = mid - 1;
                k--;
            } else if (findValue > temp[mid]) {
                //对分割点右边的数据继续进行分割
                left = mid + 1;
                k -= 2;
            } else {
                if (mid < right) {
                    return mid;
                } else {
                    return right;
                }

            }
        }
        return -1;
    }
}
