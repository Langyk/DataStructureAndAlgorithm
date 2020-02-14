package tenalgorithm.kruskal;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/14 10:24
 * @Version 1.0
 */
public class KruskalCase {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        System.out.println(Arrays.toString(kruskalCase.getEdges()));
        kruskalCase.krusal();
    }

    //构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //克鲁斯卡尔算法
    public void krusal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存”已有最小生成树“中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最小生成树
        Edata[] rets=new Edata[edgeNum];
        //获取图中的所有边的集合，一共有12个边
        Edata[] edges=getEdges();
        System.out.println("图的边的集合="+Arrays.toString(edges)+"共"+edges.length);//12
        //按照边的权值进行大小排序
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加入rests，否则不能加入
        for (int i=0;i<edgeNum;i++){
            //获取到第i条边的第一个顶点
            int p1=getPsiton(edges[i].start);
            //获取到第i条边的第2个顶点
            int p2=getPsiton(edges[i].end);
            //获取p1这个顶点在已有最小生成树中的终点
            int m=getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树的终点
            int n=getEnd(ends,p2);
            //是否构成回路
            if (m!=n){
                ends[m]=n;
                rets[index++]=edges[i];//有一条边加入到rests数组中
            }
        }
        System.out.println("最小生成树为="+Arrays.toString(rets));
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为： \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对图中的边进行排序
    public void sortEdges(Edata[] edata) {
        for (int i = 0; i < edata.length - 1; i++) {
            for (int j = 0; j < edata.length - 1 - i; j++) {
                if (edata[j].weight > edata[j + 1].weight) {
                    Edata temp = edata[j];
                    edata[j] = edata[j + 1];
                    edata[j + 1] = temp;
                }
            }
        }
    }

    private int getPsiton(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        //找不到，则返回-1
        return -1;
    }

    /**
     * @return 存放Edata的数组，形式如[['A','B',12],['B','F',7],.....]
     */
    private Edata[] getEdges() {
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edata(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点（），用于判断两个顶点的终点是否相同
     *
     * @param ends            数组就是记录了各个顶点对应的终点是那个，ends数组在遍历过程中，逐步形成
     * @param i：表示传入的顶点对对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//构建一个Edata类，它的对象实例就表示一条边
class Edata {
    char start;//边的起始点
    char end;//边的终点
    int weight;//边的权值

    //构造器
    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
