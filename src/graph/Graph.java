package graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/9 11:37
 * @Version 1.0
 */
public class Graph {
    public static void main(String[] args){
        int n=5;//结点的个数
        String Vertexs[]={"A","B","C","D","E"};
        //创建图对象
        Graph graph=new Graph(n);
        //循环添加顶点
        for(String vertex:Vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,4);
        graph.insertEdge(1,4,1);
        graph.showGraph();
    }

        private ArrayList<String> vertexList;//存储顶点的集合
        private int[][] edges;//存储图对应的领接矩阵
        private int numOfEdges;//表示边的数目

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges=0;
    }
    //返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //显示对应的图矩阵
    public void showGraph(){
        for (int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据0->"A"   1->"B"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回结点V1和V2间的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    /**
     * @param v1 表示带点的下标，即表示第几个顶点 "A"-"B"  "A"->0,"B"->1
     * @param v2 第二顶点对应的下标
     * @param weight weight表示结点间的权重
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
    }

