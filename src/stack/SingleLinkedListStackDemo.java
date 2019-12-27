package stack;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/27 18:47
 * @Version 1.0
 */

/**
 * 使用链表模拟栈
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkdList singleLinkdList = new SingleLinkdList();
        singleLinkdList.push(5);
        singleLinkdList.push(4);
        singleLinkdList.push(53);
        singleLinkdList.pop();
        singleLinkdList.printStack();
    }
}

class SingleLinkdList {
    //定义一个头节点，链表中的头结点可以和栈的top指针合二为一
    PersonNode top = null;
    //因为链表的存储是不连续的，所以不存在栈满的情况

    //判断栈空
    public boolean isEmpty() {
        return top == null;
    }

    //入栈
    public void push(int value) {
        //将数据存入节点中
        PersonNode personNode = new PersonNode(value);
        personNode.next = top;
        top = personNode;
    }

    //出栈
    public int pop() {
        if (this.isEmpty()) {
            System.out.println("输出的数据为" + top);
            return 0;
        }
        int value = top.value;
        top = top.next;
        return value;
    }

    //打印栈中的所有数据
    public void printStack() {
        System.out.println("开始出栈");
        while (!this.isEmpty()) {
            System.out.println(top.value);
            top = top.next;
        }
    }

}

class PersonNode {
    public int value;
    public PersonNode next;

    public PersonNode(int num) {
        this.value = num;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "num=" + value +
                '}';
    }
}