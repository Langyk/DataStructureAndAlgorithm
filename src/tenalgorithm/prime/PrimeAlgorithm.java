package tenalgorithm.prime;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/13 15:41
 * @Version 1.0
 */
public class PrimeAlgorithm {
    public static void main(String[] args){
        char[] data={'A','B','C','D','E','F','G'};
        int verxs=data.length;
        //邻居矩阵关系用二维数组来表示
        int[][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,9,10000,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };
        Mgraph mgraph=new Mgraph(verxs);
        MinTree minTree=new MinTree();
        minTree.createGraph(mgraph,verxs,data,weight);
        minTree.showGraph(mgraph);
        //测试prime算法
        minTree.prime(mgraph,0);
    }
}
//构建一个最小生成树
class MinTree{
    public void createGraph(Mgraph mgraph,int vertxs,char data[] ,int[][] weight){
        int i,j;
        for (i=0;i<vertxs;i++){
            mgraph.data[i]=data[i];
            for (j=0;j<vertxs;j++){
                mgraph.weight[i][j]=weight[i][j];
            }
        }

    }
    //显示图的邻接矩阵
    public void showGraph(Mgraph mgraph){
        for (int[] link:mgraph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    public void prime(Mgraph mgraph,int v){
        //标记被访问结点的数组
        int visited[]=new int[mgraph.vertexs];
        int minWeight=10000;//将两节点间的权重设置为较大的值
        int h1=-1;
       int h2=-1;
       int total=0;
        visited[v]=1;//将起始结点标记被访问
        for (int k=1;k<mgraph.vertexs;k++){//N个顶点的最小生成树含有(N-1)条边
            //已经被访问过的结点的循环
            for (int i=0;i<mgraph.vertexs;i++){
                //与访问过的结点；邻近结点
                for (int j=0;j<mgraph.vertexs;j++){
                    if (visited[i]==1&&visited[j]==0&&mgraph.weight[i][j]<minWeight){
                        minWeight=mgraph.weight[i][j];
                        h1=i;
                        h2=j;

                    }
                }
            }
            total+=minWeight;
            System.out.println("边<"+mgraph.data[h1]+","+mgraph.data[h2]+">权值"+minWeight);

            //将当前这个结点标记为已访问
            visited[h2]=1;
            minWeight=10000;
        }
        System.out.printf("总路程数为"+total);
    }
}
/**
 * 构建一个图
 */
class Mgraph{
    int vertexs;
    char[] data;
    int[][] weight;
    public Mgraph(int vertexs){
        this.vertexs=vertexs;
        data=new char[vertexs];
        weight=new int[vertexs][vertexs];
    }
}
