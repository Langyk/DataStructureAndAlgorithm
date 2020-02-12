package tenalgorithm.dynamic;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/12 11:45
 * @Version 1.0
 */
public class KnapackProblem {
    public static void main(String[] args){
    int[] w={1,4,3};//背包的重量
        int[] val={1500,3000,2000};//物品的价值
        int m=4;//背包容量
        int n=val.length;//物品的个数
        //创建二维数组;v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大值
        int[][] v=new int[n+1][m+1];
        //为了记录放入商品的情况，我们定了一个二维数组path
        int[][] path=new int[n+1][m+1];
        //初始化第一行和第一列，在本程序中，可以不去处理，默认为0
        for(int i=0;i<v.length;i++){
            v[i][0]=0;//将第一列设置为0
        }
        for (int i=0;i<v[0].length;i++){
            v[0][i]=0;//第一行设置为0
        }
        //根据公式进行动态规划处理
        for(int i=1;i<v.length;i++){//不处理第一行，i是从1开始的
            for(int j=1;j<v[0].length;j++){
                //公式
                if(w[i-1]>j){//因为i是从1开始的，所以需要从i-1开始
                    v[i][j]=v[i-1][j];
                }else {
//                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了得到具体的放置，引入了二维数组path
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        path[i][j]=1;
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }
            }

        }
        //输出显示
        int i=path.length-1;//行的最大值
        int j=path[0].length-1;//列的最大值
        while (i>0&&j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j-=w[i-1];
            }
            i--;
        }


    }
}
