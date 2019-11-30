package array;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Simon Lang
 * @Date: 2019/11/30 17:05
 * @Version 1.0
 */
public class parseArray {
    public static void main(String[] args) throws IOException, IOException {
        /**
         * 稀疏数组的实现，模拟棋盘，1代表黑子，2代表红子
         */

        /**
         * 1、构建二维数组
         */
        System.out.println("构建二维数组");
        int array[][] = new int[11][11];
        array[0][2] = 1;
        array[1][3] = 2;
        for (int i = 0; i < array[0].length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

        /**
         * 2、二维数组转为稀疏数组
         */
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        //构建稀疏数组
        int parseArray[][] = new int[sum + 1][3];
        parseArray[0][0] = array.length;
        parseArray[0][1] = array[0].length;
        parseArray[0][2] = sum;
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    parseArray[count][0] = i;
                    parseArray[count][1] = j;
                    parseArray[count][2] = array[i][j];
                    count++;
                }

            }
        }
        System.out.println("二维数组转稀疏数组");
        for (int i = 0; i < parseArray.length; i++) {
            for (int j = 0; j < parseArray[1].length; j++) {
                System.out.print(parseArray[i][j] + "\t");
            }
            System.out.println();
        }
/**
 * 将稀疏数组存贮在磁盘中
 */

        File file=new File("C:\\Users\\Administrator\\Desktop\\学习笔记\\java笔记\\DataStructureAndAlgorithm\\代码实现\\test.txt");
        FileWriter out=new FileWriter(file); //文件写入流
        for(int i=0;i<parseArray.length;i++){
            for (int j=0;j<parseArray[0].length;j++){
                out.write(parseArray[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();

        /**
         * 从磁盘读出稀疏数组
         */
        // TODO: 2019/11/28 面试一般会考核心算法，读/写磁盘相对来说不是重点
/**
 *稀疏数组转为二维数组
 */
        int array2[][] = new int[parseArray[0][0]][parseArray[0][1]];
        for (int i = 1; i < parseArray.length; i++) {
            int row = parseArray[i][0];
            int col = parseArray[i][1];
            int value = parseArray[i][2];
            array2[row][col] = value;
        }
        System.out.println("稀疏数组转二维数组");
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                System.out.print(array2[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
