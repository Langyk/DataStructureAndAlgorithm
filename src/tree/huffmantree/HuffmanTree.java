package tree.huffmantree;

import java.util.*;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/5 20:03
 * @Version 1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
       Node root= huffmanTree(arr);
       //对构建的霍夫曼树前序遍历
        preOrder(root);

    }

    public static Node  huffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        //将数组中的值转换成结点
        for (int vale : arr) {
            nodes.add(new Node(vale));
        }
        while (nodes.size() > 1) {

            //按权重的值从小到大排序
            Collections.sort(nodes);
            //取出权值较小的两个结点组成二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //以这两个结点的权重相加的值组成他们的父节点，
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //将此二叉树的两个子结点从list中移除，并将父节点加入到list中构成新的list
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);


        }
        return nodes.get(0);
    }
    public static void preOrder(Node root){
    if(root!=null){
        root.preOrder();
    }else {
        System.out.println("空树，不可遍历");
    }
    }

}

/**
 * 创建一个结点类
 */
class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }


    //创建前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
