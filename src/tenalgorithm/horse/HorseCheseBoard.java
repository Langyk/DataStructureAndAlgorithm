package tenalgorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/16 10:55
 * @Version 1.0
 */
public class HorseCheseBoard {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记棋盘的所有位置是否被访问
    private static boolean finished;//如果为true，则表示成功

    public static void main(String[] args){
        System.out.println("骑士周游算法，开始运行~~");
        X=8;
        Y=8;
        int row=1;//马儿初始位置的行，从1开始编号
        int column=1;//马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard=new int[X][Y];
        visited=new boolean[X*Y];//初始值都是false
        //测试耗时
        long start=System.currentTimeMillis();
        traversalChessboard(chessboard,row-1,column-1,1);
        long end=System.currentTimeMillis();
        System.out.println("共耗时："+(end-start)+"毫秒");
        //输出棋盘最后的情况
        for (int[] rows:chessboard){
            for (int step:rows){
                System.out.println(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游算法
     * @param chessboard  棋盘
     * @param row 马儿当前位置的行，从0开始
     * @param column  马儿当前位置的列，从0开始
     * @param step 表示第几步，初始位置就是第一步
     */
    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column]=step;
        visited[row*X+column]=true;//标记该位置已被访问
        //获取当前位置可以走的下一位置的集合
        ArrayList<Point> ps=next(new Point(column,row));
        sort(ps);
        while (!ps.isEmpty()){
            Point p=ps.remove(0);//取出下一个可以走的位置
            //判断该点是否被访问过
            if (!visited[p.y*X+p.x]){//说明还没有被访问过
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将棋盘置位0
        //1、棋盘到目前的位置，仍然没有走完
        //2、棋盘处于一个回溯过程
        if(step<X*Y&&!finished){
            chessboard[row][column] = 0;
            visited[row*X+column]=false;
        }else {
            finished=true;
        }
    }

    public static ArrayList<Point> next(Point curPoint){
        //创建一个ArrayList
        ArrayList<Point> ps=new ArrayList<Point>();
        //创建一个Point
        Point p1=new Point();
        //表示马儿可以走5这个位置
        if((p1.x=curPoint.x-2)>=0&&(p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走6这个位置
        if((p1.x=curPoint.x-1)>=0&&(p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走7这个位置
        if((p1.x=curPoint.x+1)<X && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走0这个位置
        if((p1.x=curPoint.x+2)<X &&(p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走1这个位置
        if((p1.x=curPoint.x+2)<X &&(p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //表示马儿可以走2这个位置
        if((p1.x=curPoint.x+1)<X &&(p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //表示马儿可以走3这个位置
        if((p1.x=curPoint.x-1)>=0&&(p1.y=curPoint.y+2)<0){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走4这个位置
        if((p1.x=curPoint.x-2)>=0&&(p1.y=curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1=next(o1).size();
                int count2=next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }

            }
        });
    }
}
