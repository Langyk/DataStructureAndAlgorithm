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
        PersonNode updatePerson=new PersonNode(2,"苏菲","fei");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addNode(person1);
//        singleLinkedList.addNode(person2);
//        singleLinkedList.addNode(person3);
//        singleLinkedList.addNode(person4);
        singleLinkedList.addByOrder(person4);
        singleLinkedList.addByOrder(person2);
        singleLinkedList.addByOrder(person1);
        singleLinkedList.addByOrder(person3);
        singleLinkedList.show();
//        singleLinkedList.update(updatePerson);
//        System.out.println("修改后的链表信息");
//        singleLinkedList.show();
        System.out.println("删除某节点后的链表");
        singleLinkedList.delete(person3);
        singleLinkedList.show();
    }
}

class SingleLinkedList {
    //创建头结点

    private PersonNode headNode = new PersonNode(0, "", "");

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

public void update(PersonNode personNode){
    boolean flag=false;
    PersonNode temp=headNode;
    //根据编号找到要修改的节点
    while (true){
        if(temp.next==null){
            System.out.println("链表为空");
            break;
        }
        if(temp.next.number==personNode.number){
            flag=true;
            break;
        }
        temp=temp.next;
    }
    if(flag){
        temp.next.nickName=personNode.nickName;
        temp.next.name=personNode.name;
    }else {
        System.out.println("找不到该节点的信息");
    }

}
/**
 * 删除某一个节点
 * 先根据number查找，在删除
 */
public void delete(PersonNode personNode){
    boolean flag=false;
    PersonNode temp=headNode;
    while (true){
        if(temp.next==null){
            System.out.println("链表为空");
            break;
        }
        if(temp.next.number==personNode.number){
            flag=true;
            break;
        }
        temp=temp.next;
    }
    if(flag){
        temp.next=temp.next.next;
    }else {
        System.out.println("找不到该节点的信息");
    }

}
    /**
     * 遍历节点
     */
    //显示链表中的有效节点
    public void show() {
        if (headNode.next == null) {
            System.out.println("该链表为空~~~");
        }
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