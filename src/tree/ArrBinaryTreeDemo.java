package tree;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/4 13:06
 * @Version 1.0
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree abt = new ArrayBinaryTree(arr);
//        abt.preOrder();
//        abt.infixOrder();
        abt.postOrder();
    }
}

/**
 * 定义一个二叉树
 */
class ArrayBinaryTree {
    public int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

public void preOrder(){
        preOrder(0);
}
public void infixOrder(){
        infixOrder(0);
}
public void postOrder(){
        postOrder(0);
}
    //1、前序遍历
    public void preOrder(int index) {
        if (arr != null && arr.length > 0) {
            System.out.println(arr[index]);
            if (index * 2 + 1 < arr.length) {
                //在左结点递归
                preOrder(index * 2 +1);
            }
            if (index * 2 + 2 < arr.length) {
                //在右结点递归
                preOrder(index * 2 +2);
            }
        }
    }
    //2、中序遍历
    public void infixOrder(int index) {
        if (arr != null && arr.length > 0) {
            if (index * 2 + 1 < arr.length) {
                //在左结点递归
                infixOrder(index * 2 +1);
            }
            System.out.print(arr[index]);
            if (index * 2 + 2 < arr.length) {
                //在右结点递归
                infixOrder(index * 2 +2);
            }
        }
    }
    //3、后序遍历
    public void postOrder(int index) {
        if (arr != null && arr.length > 0) {
            if (index * 2 + 1 < arr.length) {
                //在左结点递归
                postOrder(index * 2 +1);
            }
            if (index * 2 + 2 < arr.length) {
                //在右结点递归
                postOrder(index * 2 +2);
            }
            System.out.print(arr[index]);
        }
    }
}
