package weekly;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/24 16:30
 * @Version 1.0
 */

/**
 * 问题简介
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排列，每一列都按照从上到下递增顺序排序。
 * 完成一个函数，输入这样的一个二维数组和一个整数，判断组中是否含有该整数。
 */
public class Week01Test01 {
    public static void main(String[] args) {
        //构造数组
        int arrTest[][]={{1,2,3,4,100},
                            {5,6,9,101},
                            {7,99,100,102}};
        /**
         * arrTest[row][col]
         * 查找规则：从右上角开始，比较输入的值value与数组中右上角的值，
         *   如果小于右上角的值，如果大于右上角的值col--，
         *   如果大于右上角的值，row++，
         *   继续比较arrTest[row][col]与value
         */
        Week01Test01 week01Test01=new Week01Test01();
        Boolean flag=week01Test01.isExist(arrTest,9);
        if(flag){
            System.out.println("该值存在数组中");
        }else {
            System.out.println("该值不存在数组中");
        }

    }

    //编写一个查找方法,从右上角开始查找
    public boolean isExist(int [][] arrTest,int value){
        int row=0;
        int col=arrTest[1].length-1;
       while (row<arrTest.length&&col>=0){
           if(value==arrTest[row][col]){
               return true;
           }
           else if(arrTest[row][col]>value){
               col--;
           }
           else {
               row++;
           }
       }
       return false;
    }

}
