package linkedList;

import java.util.Stack;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/1 14:25
 * @Version 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        PersonNode person1 = new PersonNode(1, "郎亚坤", "Simon");
        PersonNode person2 = new PersonNode(2, "苏超", "suf");
        PersonNode person3 = new PersonNode(3, "杨金凯", "Kevin");
        PersonNode person4 = new PersonNode(4, "孙祥胜", "sun");


        PersonNode updatePerson = new PersonNode(2, "苏菲", "fei");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addNode(person1);
//        singleLinkedList.addNode(person2);
//        singleLinkedList.addNode(person3);
//        singleLinkedList.addNode(person4);
        singleLinkedList.addByOrder(person4);
        singleLinkedList.addByOrder(person2);
        singleLinkedList.addByOrder(person1);
        singleLinkedList.addByOrder(person3);
        singleLinkedList.show(singleLinkedList.getHeadNode());

//        singleLinkedList.update(updatePerson);
//        System.out.println("修改后的链表信息");
//        singleLinkedList.show();
//        System.out.println("删除某节点后的链表");
//        singleLinkedList.delete(person3);
//        singleLinkedList.show();
/**
 * 面试题测试
 */
//        int length = singleLinkedList.getLength();
//        System.out.println("链表中有效节点的个数为" + length);
//
//        PersonNode personNode=singleLinkedList.getindex(3);
//        System.out.println(personNode);
//        System.out.println("单链表的反转~~~");
//        singleLinkedList.reverseSingleList(singleLinkedList.getHeadNode());
//        singleLinkedList.show(singleLinkedList.getHeadNode());
        System.out.println("逆序输出单链表");
        singleLinkedList.reversePrint(singleLinkedList.getHeadNode());
    }
}

class SingleLinkedList {
    //创建头结点

    private PersonNode headNode = new PersonNode(0, "", "");

    public PersonNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(PersonNode headNode) {
        this.headNode = headNode;
    }
//添加节点的单向链表

    /**
     * 在链表尾部插入
     */

    public void addNode(PersonNode personNode) {
        //定义个替代变量
        PersonNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = personNode;
    }

    /**
     * 按照number顺序插入
     */
    public void addByOrder(PersonNode personNode) {
        //定义插入标识符
        boolean flag = false;
        //定义替代变量
        PersonNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //满足条件
            if (temp.next.number > personNode.number) {
                break;
            } else if (temp.next.number == personNode.number) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        //插入节点
        if (flag) {
            System.out.println("节点已经存在~~~~");
        } else {
            personNode.next = temp.next;
            temp.next = personNode;
        }
    }

    /**
     * 修改节点信息
     */

    public void update(PersonNode personNode) {
        boolean flag = false;
        PersonNode temp = headNode;
        //根据编号找到要修改的节点
        while (true) {
            if (temp.next == null) {
                System.out.println("链表为空");
                break;
            }
            if (temp.next.number == personNode.number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next.nickName = personNode.nickName;
            temp.next.name = personNode.name;
        } else {
            System.out.println("找不到该节点的信息");
        }

    }

    /**
     * 删除某一个节点
     * 先根据number查找，在删除
     */
    public void delete(PersonNode personNode) {
        boolean flag = false;
        PersonNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                System.out.println("链表为空");
                break;
            }
            if (temp.next.number == personNode.number) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("找不到该节点的信息");
        }

    }

    /**
     * 遍历节点
     */
    //显示链表中的有效节点
    public void show(PersonNode personNode) {
        if (personNode.next == null) {
            System.out.println("该链表为空~~~");
        }
        PersonNode temp = personNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }


    /***
     * 面试题
     * 1、查询链表中有效节点的个数
     * 2、查找链表中倒数第K个节点
     * 3、单链表的反转
     * 4、从尾到头打印单链表
     */
    public int getLength() {
        int length = 0;
        //构造一个临时节点
        PersonNode temp = headNode;
        temp = temp.next;
        if (temp == null) {
            return 0;
        }
        while (true) {
            temp = temp.next;
            length++;

            if (temp == null) {
                break;
            }
        }

        return length;
    }

    /**
     * 2、查找链表中倒数第K个节点
     * 思路：先找出有效节点的总个数length，倒数第K个节点为：证书第（Length-k）个节点
     */
    public PersonNode getindex(int k) {
        int length = this.getLength();
        PersonNode temp = headNode;
        if (length == 0 && k < 0 && k > length) {
            return null;
        }
        for (int i = 0; i < length - k + 1; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 3、单链表的反转
     * 思路：重新定义一个单链表的头结点reverseHead
     * 遍历原来的链表，将原始链表的有效节点依次加入到reverseHead头结点的最前端
     * 将原始节点的头结点指向新建链表的第一个有效节点,即head.next=reverseHead.next
     */

    public void reverseSingleList(PersonNode head) {
        //定义一个临时变量
        PersonNode cur = head.next; //当前的有效节点
        PersonNode next = null;//当前有效节点的下一个节点
        //定义一个反转链表的头结点
        PersonNode reverseHeadNode = new PersonNode(0, "", "");

        while (cur != null) {
            //判断是否需要反转，原始链表为空或者只有一个有效结点时，则不需要反转
            if (cur == null || cur.next == null) {
                break;
            }
            next = cur.next;//指向当前有效结点的下一个节点
            cur.next = reverseHeadNode.next;//将有效结点连接起来
            reverseHeadNode.next = cur;//将原始链表的有效结点放置到reverseHead的最前端
            cur = next;
        }
        //将原始链表的头结点指向反转链表的第一个有效结点
        head.next = reverseHeadNode.next;

    }

    /**
     * 4、从尾到头打印单链表（两种方式）
     * 方式1：将单链表反转后打印，但是会破坏原始的单链表的结构
     * 方式2：利用栈的先入后出的特性进行单链表的打印。
     */
    public void reversePrint(PersonNode head) {
        Stack stack = new Stack<PersonNode>();
        PersonNode cur = head.next;
        //压栈
        while (true) {
            if (cur == null) {
                break;
            }
            stack.add(cur);
            cur = cur.next;
        }
        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

}

/**
 * 创建节点对象
 */
class PersonNode {
    public int number;
    public String name;
    public String nickName;
    public PersonNode next;

    public PersonNode(int number, String name, String nickName) {
        this.number = number;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}