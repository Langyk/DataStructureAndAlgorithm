package tree.binarysorttree;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/7 14:31
 * @Version 1.0
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinSortTree bst = new BinSortTree();
        for (int i = 0; i < arr.length; i++) {
            bst.add(new Node(arr[i]));
        }
        bst.infixOrder();
/********************测试删除结点******************************/
        System.out.println("中序遍历删除指定结点的排序二叉树");
        bst.delNode(7);
        bst.infixOrder();
    }
}

/**
 * 构造一个排序二叉树
 */
class BinSortTree {
    private Node root;

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /*************************删除指定的结点**************************************/
//查找要删除的指定结点
    public Node searchTargetNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchTargetNode(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParentNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParentNode(value);
        }
    }

    /**
     * @param node node传入的结点（当做二叉排序树的根结点）
     * @return 返回以node 为根结点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环的查找左子结点，就会找到最小值
        while (target.left!=null){
            target=target.left;
        }
        //此时target就指向了最小结点，将最小结点删除
        delNode(target.value);
        return target.value;
    }

    //删除叶子结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1、找到要删除的目标结点
            Node targetNode = searchTargetNode(value);
            if (targetNode == null) {
                return;
            }
            //如果发现这颗二叉树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //2、找到要删除结点的父结点
            Node parentNode = searchParentNode(value);
            //如果要删除的结点为叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是parentNode的左子结点还是右子结点
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    parentNode.right = null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){
                //删除的结点含有两颗子树
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;
            }else {
                //删除的结点含有一颗子树的
                //如果目标结点的子结点是左子结点
                if(targetNode.left!=null){
                    if(parentNode!=null){
                        if(parentNode.left.value==value){
                            parentNode.left=targetNode.left;
                        }else {
                            parentNode.right=targetNode.left;
                        }
                    }else {
                        root=targetNode.left;
                    }

                }else {
                    if(parentNode!=null){
                        if(parentNode.left.value==value){
                            parentNode.left=targetNode.right;
                        }else {
                            parentNode.right=targetNode.right;
                        }
                    }else {
                        root=targetNode.right;
                    }

                }
            }

        }
    }

    //遍历显示
    public void infixOrder() {
        if (root == null) {
            System.out.println("该二叉树为空");
        } else {
            root.infixOrder();
        }
    }
}

/**
 * 构造一个结点类
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加结点
    public void add(Node node) {
        //判断添加的结点是否为空
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }

        if (node.value > this.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**************************删除结点*****************************/
    //查找要删除的目标结点
    public Node searchTargetNode(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.searchTargetNode(value);
        } else if (value > this.value && this.right != null) {
            return this.right.searchTargetNode(value);
        }
        return null;
    }

    //查找要删除结点的父结点
    public Node searchParentNode(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.searchParentNode(value);
        } else if (value >= this.value && this.right != null) {
            return this.right.searchParentNode(value);
        } else {
            return null;
        }

    }

    //遍历排序二叉树(中序遍历)
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
