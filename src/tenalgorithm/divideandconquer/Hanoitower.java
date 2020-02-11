package tenalgorithm.divideandconquer;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/11 21:22
 * @Version 1.0
 */
public class Hanoitower {
    public static void main(String[] args){
        hanoitower(3,'A','B','C');
    }
    public static void hanoitower(int num ,char a,char b,char c){
        //只有一个盘
        if(num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else {
            //如果n>=2，我们总可以把整体看做两个盘，：最下边的盘A和除下边的所有盘B
            //1、先把最上面的所有盘A->B,移动过程会使用到C
            hanoitower(num-1,a,c,b);
            //2、把最下边的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //把B塔中的所有盘B->C,移动过程中使用到a塔
            hanoitower(num-1,b,a,c);
        }
    }
}
