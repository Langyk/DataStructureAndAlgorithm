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
        /**方法一：
         * arrTest[row][col]
         * 查找规则：从右上角开始，比较输入的值value与数组中右上角的值，
         *   如果小于右上角的值，如果大于右上角的值col--，
         *   如果大于右上角的值，row++，
         *   继续比较arrTest[row][col]与value
         *   时间复杂度O(m+n)
         */
        Week01Test01 week01Test01=new Week01Test01();
        Boolean flag=week01Test01.isExist(arrTest,9);
        if(flag){
            System.out.println("该值存在数组中");
        }else {
            System.out.println("该值不存在数组中");
        }
        //方法二：
        Boolean flag2=week01Test01.isExist2(arrTest,9);
        if(flag){
            System.out.println("该值存在数组中");
        }else {
            System.out.println("该值不存在数组中");
        }

    }

    /**
     *编写一个查找方法,从右上角开始查找
     */
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

    /**
     * 方法二：既然每行，每列都是递增，可以拆成对每一行进行二分查找，时间复杂度为logm
     */
    public boolean isExist2(int[][] arrTest,int Value){
        for(int i=0;i<arrTest.length;i++){
            int low=0;
            int high=arrTest[i].length-1;
            while (low<high){
                int middle=(low+high)/2;
                if(Value==arrTest[i][middle]){
                    return true;
                }else if(Value>arrTest[i][middle]){
                    low=middle+1;
                }else if(Value<arrTest[i][middle]){
                    high=middle-1;
                }
            }
        }
        return false;
    }
    /**
     * 方法三：行、列都使用二分查找
     */
    public boolean isExist3(int[][] arrTest,int Value){

        return false;
    }


}
