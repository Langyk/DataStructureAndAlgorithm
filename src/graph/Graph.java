package graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/9 11:37
 * @Version 1.0
 */
public class Graph {
    public static void main(String[] args) {
        int n = 5;//结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);

        //循环添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 4);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        //测试图的遍历
        graph.dfs();
    }

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的领接矩阵
    private int numOfEdges;//表示边的数目
    //创建顶点访问数组isVisited
    boolean isVisited[];

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited=new boolean[5];

    }
/************************图的遍历***************************/
    /**
     * @param index
     * @return 如果存在返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
    for(int j=0;j<vertexList.size();j++){
        if(edges[index][j]>0){
            return j;
        }
    }
    return -1;
}
//根据前一个邻接结点的小标获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
         for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }
//深度优选遍历算法DFS
    //i 第一次就是0
    public void dfs(boolean[] isVisited,int i){
        //首先我们访问该结点，然后刷输出
        System.out.println(getValueByIndex(i));
        //将结点设置为已访问
        isVisited[i]=true;
        //查找i的第一个邻接结点w
        int w=getFirstNeighbor(i);
        while (w!=-1){//说明有
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //若w已经被访问过
            w=getNextNeighbor(i,w);
        }
    }
    //对dfs进行一个重载，遍历所有的结点
    public void dfs(){
        //遍历所有的结点，进行dfs[回溯]
        for (int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

/******************************************************/
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示对应的图矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i(下标)对应的数据0->"A"   1->"B"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回结点V1和V2间的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边

    /**
     * @param v1     表示带点的下标，即表示第几个顶点 "A"-"B"  "A"->0,"B"->1
     * @param v2     第二顶点对应的下标
     * @param weight weight表示结点间的权重
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}

