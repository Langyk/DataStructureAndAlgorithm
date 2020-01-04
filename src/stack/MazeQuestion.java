package stack;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/3 21:00
 * @Version 1.0
 * <p>
 * 有一个迷宫地图，有一些可达的位置，也有一些不可达的位置（障碍、墙壁、边界）
 * 。从一个位置到下一个位置只能通过向上（或者向右、或者向下、或者向左）走一步来实现，从起点出发，如何找到一条到达终点的通路。
 */

/**
 * 有一个迷宫地图，有一些可达的位置，也有一些不可达的位置（障碍、墙壁、边界）
 *。从一个位置到下一个位置只能通过向上（或者向右、或者向下、或者向左）走一步来实现，从起点出发，如何找到一条到达终点的通路。
 */

/**
 * 分析：
 * 定义一个二维数组，1表示位置不可到达，2表示通路可以走，3表示位置已经走过，走不通，0表示还未走过的点
 * 采用递归的算法
 * 1、map表示地图
 * 2、i,j表示从地图的那个位置开始出发（1,1）
 * 3、如果小球到map[6][5]位置，则说明通路找到
 * 4、策略：下->右->上->左（算法的优化是策略的变化）
 */
public class MazeQuestion {
    public static void main(String[] args) {
    //定义一个二维数组
        int[][] map=new int[8][7];
        //设置障碍
        //上下墙壁
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右墙壁
        for(int i=1;i<7;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
        //打印迷宫地图
        System.out.println("打印原始迷宫地图");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("\n");
        }
        //探索后的迷宫地图
        System.out.println("探索后迷宫地图");
        setWay(map,1,1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    public static boolean setWay(int map[][],int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2; //假定该路可以走通
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }
                else if(setWay(map,i,j+1)){ //向右走
                    return true;
                }
                else if(setWay(map,i-1,j)){//向上走
                    return true;
                }
                else if(setWay(map,i,j-1)){//向左走
                    return true;
                }else {//说明此路不通
                    map[i][j]=3;
                    return false;
                }
            }else {//map[i][j]可能为1,2,3
                return false;
            }
        }
    }
}
