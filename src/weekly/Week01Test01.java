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
        int arrTest[][];
        Week01Test01 week01Test01 = new Week01Test01();
        /**
         * 1	2	3	4	5	6	7	8	9
         * 2	3	4	5	6	7	8	9	10
         * 3	4	5	6	7	8	9	10	11
         * 4	5	6	7	8	9	10	11	12
         * 5	6	7	8	9	10	11	12	13
         * 只需要遍历最外侧（右上）即可查询到所有的数据
         */
        //构造二维数组，并判断二维数组中是否存在特定的值
        boolean flag = week01Test01.isExist(5, 9,21);
        if(flag){
            System.out.println("该整数存在二维该数组中");
        }else {
            System.out.println("该值不存在");
        }



    }


    //编写一个构造数组的函数

    public int[][] structureArray(int m, int n) {
        int array[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            int value = i + 1;
            for (int j = 0; j < n; j++) {
                array[i][j] = value;
                value++;
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        return array;
    }
    //编写一个判断整数是否存在二维数组中的方法
    public boolean isExist(int m,int n,int value){
        int array[][]=this.structureArray(m,n);
        for (int i = 0; i < m; i++) {
            if(i==0){
                for(int j=0;j<n;j++){
                    if(value==array[i][j]){
                        return true;
                    }

                }
            }
            if(value==array[i][n-1]){
                return true;
            }

        }
        return false;
    }

}
