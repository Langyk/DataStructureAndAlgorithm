package tree.threadedbinarytree;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/4 16:45
 * @Version 1.0
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args){
        PersonNode personNode1 = new PersonNode(1, "Simon");
        PersonNode personNode2 = new PersonNode(3, "Kevin");
        PersonNode personNode3 = new PersonNode(6, "Snow");
        PersonNode personNode4 = new PersonNode(8, "Eidd");
        PersonNode personNode5=new PersonNode(10,"sisi");
        PersonNode personNode6=new PersonNode(14,"wang");
        //构造二叉树
        personNode1.setLeftNode(personNode2);
        personNode1.setRightNode(personNode3);
        personNode2.setLeftNode(personNode4);
        personNode2.setRightNode(personNode5);
        personNode3.setLeftNode(personNode6);
        //测试中序线索化
        TreadedBinaryTree tbt=new TreadedBinaryTree(personNode1);
        tbt.threadedNodes();

        PersonNode leftNode=personNode5.getLeftNode();
        PersonNode rightNode=personNode5.getRightNode();
        System.out.println(leftNode);
        System.out.println(rightNode);


    }
}

/**
 * 构造线索化二叉树
 */
class TreadedBinaryTree{
    private PersonNode root;

    public TreadedBinaryTree(PersonNode root) {
        this.root = root;
    }

    public void setRoot(PersonNode root) {
        this.root = root;
    }
    /******************************采用中序遍历的方式构造线索化二叉树******************************************/
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //定义当前结点的前驱结点
    PersonNode pre=null;
    //参数中的node为需要线索化的结点
    public void threadedNodes(PersonNode node){
        if(node==null){
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeftNode());
        //线索化当前结点
        if(node.getLeftNode()==null){
            //让当前结点的左指针指向前驱结点
            node.setLeftNode(pre);
            //修改当前结点的左指针类型
            node.setLeftNodeType(1);
        }
        //处理后继结点
        if(pre!=null&&pre.getRightNode()==null){
            //让前驱结点的右指针指向当前结点
            pre.setRightNode(node);
            //修改前驱结点的右指针类型
            pre.setRightNodeType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点。
        pre=node;
        //线索化右子树
        threadedNodes(node.getRightNode());
    }

  /***********************************遍历************************************/
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
    /***************************遍历查找指定的结点*****************************************/
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
/****************************删除指定的结点****************************************/
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
 * 定义结点信息
 */
class PersonNode {
    private int no;
    private String name;
    private PersonNode leftNode;
    private PersonNode rightNode;
    //定义结点类型，若leftType为0，表示当前结点的左子结点为为子树结点，若leftType为1，表示为当前结点的左子结点为前驱结点,rightNodeType同理
    private int leftNodeType;
    private int rightNodeType;

    public PersonNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftNodeType() {
        return leftNodeType;
    }

    public void setLeftNodeType(int leftNodeType) {
        this.leftNodeType = leftNodeType;
    }

    public int getRightNodeType() {
        return rightNodeType;
    }

    public void setRightNodeType(int rightNodeType) {
        this.rightNodeType = rightNodeType;
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