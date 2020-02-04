package tree;

import jdk.nashorn.internal.objects.NativeUint8Array;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/3 16:15
 * @Version 1.0
 */
public class BinaryTree {
    public static void main(String[] args) {
        PersonNode personNode1 = new PersonNode(1, "Simon");
        PersonNode personNode2 = new PersonNode(2, "Kevin");
        PersonNode personNode3 = new PersonNode(3, "Snow");
        PersonNode personNode4 = new PersonNode(4, "Eidd");
        PersonNode personNode5=new PersonNode(5,"sisi");
        //手动构造二叉树
        personNode1.setLeftNode(personNode2);
        personNode1.setRightNode(personNode3);
        personNode3.setLeftNode(personNode5);
        personNode3.setRightNode(personNode4);
        BinTree bt = new BinTree(personNode1);
        //遍历
//        System.out.println("前序遍历");
//        bt.preOrder();
//        System.out.println("中序遍历");
//        bt.infixOrder();
//        System.out.println("后序遍历");
//        bt.postOrder();
        //查找指定的结点
        //1、
//        PersonNode resNode=bt.preOrderSearch(15);
//        PersonNode resNode=bt.infixOrderSearch(4);
//        PersonNode resNode=bt.postOrderSearch(15);
//        if(resNode!=null){
//            System.out.printf("the no=%d  the name =%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.println("没有找到该节点");
//        }
        //删除指定的结点
        System.out.println("删除前");
        bt.preOrder();
        System.out.println("删除后");
        bt.delNode(3);
        bt.preOrder();

    }
}

/**
 * 定义一个二叉树类
 */
class BinTree {
    private PersonNode root;

    public BinTree(PersonNode root) {
        this.root = root;
    }

    public void setRoot(PersonNode root) {
        this.root = root;
    }

    //1、前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //2、中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //3、后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //1、前序遍历查找
    public PersonNode preOrderSearch(int no){
        if(this.root!=null){
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //2、中序遍历查找
    public PersonNode infixOrderSearch(int no){
        if(this.root!=null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //3、后序遍历查找
    public PersonNode postOrderSearch(int no){
        if(this.root!=null){
            return this.root.postNodeSearch(no);
        }else {
            return null;
        }
    }

    //删除指定的结点
    public void delNode(int no){
        if(this.root!=null){
            if(this.root.getNo()==no){
                this.root=null;
            }else {
                this.root.delNode(no);
            }
        }else {
            System.out.println("该二叉树为空树");
        }
    }
}

/**
 * 定义一个节点类
 */
class PersonNode {
    private int no;
    private String name;
    private PersonNode leftNode;
    private PersonNode rightNode;

    public PersonNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(PersonNode leftNode) {
        this.leftNode = leftNode;
    }

    public PersonNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(PersonNode rightNode) {
        this.rightNode = rightNode;
    }

    //定义遍历的方案 1、前序遍历 2、中序遍历 3、后序遍历
    //1、前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    //2、中序遍历
    public void infixOrder() {
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    //3、后序遍历
    public void postOrder() {
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    //1、前序遍历查找
    public PersonNode preOrderSearch(int no) {
        //判断当前节点是否为要查找的节点
        if (this.no == no) {
            return this;
        }
        //判断当前节点的左节点是否为空，不为空时继续左递归查找
        PersonNode resNode = null;
        if (this.leftNode != null) {
            resNode = this.leftNode.preOrderSearch(no);
        }
        //判断左递归后查询的节点是为空
        if (resNode != null) {
            return resNode;
        }
        //若左递归查询无果，则香油递归，直接返回结果
        if (this.rightNode != null) {
            resNode = this.rightNode.preOrderSearch(no);
        }
        return resNode;
    }

    //2、中序遍历查找
    public PersonNode infixOrderSearch(int no) {
        PersonNode resNode = null;
        //判断当前节点的左节点是否为空，不为空时进行中序递归判断
        if (this.leftNode != null) {
            resNode = this.leftNode.infixOrderSearch(no);
        }
        //判断递归后的值是否为空，不为空，则证明在左子树上找到
        if (resNode != null) {
            return resNode;
        }
        //判断当前结点是否为自己想要查找的
        if (this.no == no) {
            return this;
        }
        //若坐左子树和当前结点都不是我们要查询的，则判断右结点是否为空，不为空时，则进行中序查找递归
        if (this.rightNode != null) {
            resNode = this.rightNode.infixOrderSearch(no);
        }
        return resNode;
    }

    //3、后续遍历查找
    public PersonNode postNodeSearch(int no) {
        PersonNode resNode = null;
        //判断当前结点的左结点是否存在，若存在则进行左结点的后续遍历查找递归
        if (this.leftNode != null) {
            resNode = this.leftNode.postNodeSearch(no);
        }
        //左子树查找到
        if (resNode != null) {
            return resNode;
        }
        //若没有左子树查找到，则判断右结点是否存在，存在时进行后序遍历查找递归
        if (this.rightNode != null) {
            resNode= this.rightNode.postNodeSearch(no);
        }
        //右子树查找到
        if(resNode!=null){
            return resNode;
        }
        //判断当前节点是否为要查找的结点
        if (this.no == no) {
            resNode= this;
        }
        return resNode;
    }

    //删除指定的结点
    public void delNode(int no){
        //判断左边结点是否为空，非空时判断是否为要查找的结点
        if(this.leftNode!=null&&this.leftNode.no==no){
            this.leftNode=null;
        }
        //判断右边结点是否为空，非空时判断是否为要查找的结点
        if(this.rightNode!=null&&this.rightNode.no==no){
            this.rightNode=null;
        }
        //判断左子结点是非为空，不为空时进行递归查找删除
        if(this.leftNode!=null){
            this.leftNode.delNode(no);
        }
        //判断右子结点是非为空，不为空时进行递归查找删除
        if(this.rightNode!=null){
            this.rightNode.delNode(no);
        }
    }



    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}