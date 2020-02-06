package tree.huffmanCode;

import sun.rmi.runtime.NewThreadAction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/6 11:26
 * @Version 1.0
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        //将字符数组转成byte数组
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodebytes = huffmanCodeZip(contentBytes);
//        System.out.println(Arrays.toString(huffmanCodebytes));
//        System.out.println(huffmanCodebytes.length);

        /***************************w文件压缩解压********************************/
        String srcFile="d://src.bmp";
        String dstFile="d://dst.zip";
        zipFile(srcFile,dstFile);
        System.out.println("压缩文件OK");
        /**
         *  压缩率（40-17）/40=57%
         */
        /*******************解码***************************************/
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodebytes);
        System.out.println(new String(sourceBytes));
//        System.out.println(content.length());
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
////        preOrder(huffmanTreeRoot);
//        huffmanTreeRoot.preOrder();
//        getCodes(huffmanTreeRoot, "", stringBuilder);
//        System.out.println(huffmanCodes);
//        byte[] huffmanCodeBytes=zip(contentBytes,huffmanCodes);
//        System.out.println(Arrays.toString(huffmanCodeBytes));
//        System.out.println(huffmanCodeBytes.length);

    }
    /*******************************文件压缩*******************************************************/
//编写一个方法，将文件压缩

    /**
     * @param srcFile 源文件路径
     * @param dstFile  目标文件路径
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os=null;
        ObjectOutputStream oos=null;
        //创建输入流
        FileInputStream is=null;
        try {
            //创建文件输入流
            is=new FileInputStream(srcFile);
            //创建一个和源文件大小的byte[]
            byte[] b=new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes=huffmanCodeZip(b);
            //创建文件的输出流，存放压缩文件
            os=new FileOutputStream(dstFile);
            //创建一个和文件输出流有关的ObjectOutPutStream
            oos=new ObjectOutputStream(os);
            //将霍夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //写入霍夫曼编码，是为了恢复原文件使用
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /*************************解码部分****************************************************/


//将[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]字节数组转成二进制的字符串"1000111000"
    //对照霍夫曼表生成对应的字符串"1000111000"===>"i like like like java do you like a java"

    //编写一个方法，完成对压缩数据的解码
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1、先得到huffmanBytes，对应的二进制字符串"1000111000"
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //将字符串按照指定的霍夫曼编码进行解码
        //将霍夫曼编码表进行调换，可以进行反向查询，即 a->100,
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合存放Byte
        List<Byte> list = new ArrayList<>();
        //i可以理解为索引扫描StringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100000111.....
                //递增取出key
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for循环结束后，list中就存放了"i like like like java do you like a java"
        //把list中的数据放入到byte[],并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * j将byte转成二进制字符串
     *
     * @param flag 标志着是否需要补高位，为true时需要补高位，false时不需要
     * @param b    传入的byte
     * @return 对应的二进制字符串
     */
    private static String byteToBitString(boolean flag, byte b) {
        //将b转成int
        int temp = b;
        //如果是正数我们需要补高位
        if (flag) {
            temp |= 256;//与256按位或
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }
    /***************************编码部分*************************************/
    /**
     * 对霍夫曼的所有方法进行封装，传进来一个原始的未压缩的bytes[]数组，返回一个压缩后的byte[]数组
     *
     * @param bytes 未压缩的bytes[]数组
     * @return
     */
    private static byte[] huffmanCodeZip(byte[] bytes) {
        //将bytes数组转成集合
        List<Node> nodes = getNodes(bytes);
        //构造霍夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据霍夫曼树进行霍夫曼编码
        getCodes(huffmanTreeRoot, "", stringBuilder);
        //对生成的霍夫曼编码进行压缩
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    //编写一个方法，将字符串对应的byte[]数组，根据霍夫曼表，对数据压缩，返回一个霍夫曼压缩后的byte[]数组
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //遍历byte数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //煤8位转成一个byte
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte，放入到huffmanCodeBytes,二进制每8位表示一个byte
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //构造编码表的形式{32=01，97=11000，100=11000 ....}
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //生成霍夫曼编码表需要拼接路径，定义StringBuilder(字符串可变),存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.leftNode, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.rightNode, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 得到所有叶子结点的霍夫曼编码，放入到huffmanCode集合中
     *
     * @param node          传入结点
     * @param code          路径，左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                //向左递归
                getCodes(node.leftNode, "0", stringBuilder2);
                //向右递归
                getCodes(node.rightNode, "1", stringBuilder2);
            } else {
                //表明是叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //重载前序遍历方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("该二叉树为空树");
        }
    }

    //构造霍夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.leftNode = leftNode;
            parentNode.rightNode = rightNode;
            //在集合中删除子结点并加入父结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    //将byte数组转成结点的集合
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }

        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }


}

/**
 * 创建一个结点类
 */
class Node implements Comparable<Node> {
    public Byte data;  //字符都是在计算底层是采用位计算、存储的 'A'-->97
    public int weight; //字符出现的频率
    public Node leftNode;
    public Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //从小到大排列
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}