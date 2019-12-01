package LinkedList;

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
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        singleLinkedList.addNode(person1);
        singleLinkedList.addNode(person2);
        singleLinkedList.addNode(person3);
        singleLinkedList.addNode(person4);
        singleLinkedList.show();
    }
}

class SingleLinkedList {
    //创建头结点

    private PersonNode headNode = new PersonNode(0, "", "");

    //添加节点的单向链表
    public void addNode(PersonNode personNode) {
        //定义个替代变量
        PersonNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp=temp.next;
        }
        temp.next=personNode;
    }

    //显示链表中的有效节点
    public void show() {
        PersonNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

/**
 * 创建节点对象
 */
class PersonNode {
    private int number;
    private String name;
    private String nickName;
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