package LinkedList;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/25 17:03
 * @Version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkeList doubleLinkeList = new DoubleLinkeList();
        PersonNode2 person1 = new PersonNode2(1, "郎亚坤", "Simon");
        PersonNode2 person2 = new PersonNode2(2, "苏超", "suf");
        PersonNode2 person3 = new PersonNode2(3, "杨金凯", "Kevin");
        PersonNode2 person4 = new PersonNode2(4, "孙祥胜", "sun");

//        System.out.println("查询双向链表的有效节点~~~");
//        doubleLinkeList.add(person2);
//        doubleLinkeList.add(person4);
//        doubleLinkeList.add(person3);
//        doubleLinkeList.add(person1);
//        doubleLinkeList.show(doubleLinkeList.getHeadNode());
        System.out.println("按顺序添加~~");
        doubleLinkeList.addByOrder(person2);
        doubleLinkeList.addByOrder(person4);
        doubleLinkeList.addByOrder(person3);
        doubleLinkeList.addByOrder(person1);
        doubleLinkeList.show(doubleLinkeList.getHeadNode());
//        PersonNode2 updatePerson = new PersonNode2(2, "苏菲", "fei");
//        System.out.println("修改双向链表");
//        doubleLinkeList.update(updatePerson);
//        doubleLinkeList.show(doubleLinkeList.getHeadNode());
//        doubleLinkeList.delete(person2);
//        doubleLinkeList.show(doubleLinkeList.getHeadNode());
    }
}

class DoubleLinkeList {
    //创建一个头结点
    PersonNode2 headNode = new PersonNode2(0, "", "");

    public PersonNode2 getHeadNode() {
        return headNode;
    }

    /**
     * 1、添加节点
     */
    public void  add(PersonNode2 personNode2){
        PersonNode2 temp=headNode;

        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //构成双向链表
        temp.next=personNode2;
        personNode2.pre=temp;
    }

    public void addByOrder(PersonNode2 personNode2){
        PersonNode2 temp=headNode;
        boolean flag=false;
        boolean flag2=false;
        boolean flag3=false;
        while (true){
            if (temp.next==null){
                flag2=true;
                break;
            }
            //加入两个节点间的条件
            if(temp.next.number>personNode2.number){
                    flag3=true;
                    break;
            }
            if(temp.next.number==personNode2.number){
                flag=true;
                break;
            }

            temp=temp.next;
        }
        if(flag){
            System.out.println("相同的节点已存在");
        }
        if(flag2){
            //在末尾添加构成双向链表
            temp.next=personNode2;
            personNode2.pre=temp;
        }
        if(flag3){
            //在两个有效节点间添加
            personNode2.pre= temp;
            personNode2.next=temp.next;
            temp.next.pre=personNode2;
            temp.next=personNode2;
        }



    }
    /**
     * 2、修改节点
     */

    public void update(PersonNode2 personNode2){
        PersonNode2 temp=headNode;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.number==personNode2.number){
                temp.next.name=personNode2.name;
                temp.next.nickName=personNode2.nickName;
            }
            temp=temp.next;
        }
    }

    /**
     * 3、删除节点
     */

    public  void delete(PersonNode2 personNode2){
        PersonNode2 temp=headNode;
        boolean flag=false;
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
            if(temp.number==personNode2.number){
                flag=true;
                break;
            }

        }
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }

        }else {
            System.out.println("找不到删除节点的信息~~");
        }

    }

    /**
     * 4、遍历
     */
    public void show(PersonNode2 head){
        PersonNode2 temp=head;
        if(temp.next==null){
            System.out.println("该链表为空");
        }
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
            System.out.println(temp);
        }
    }
}

class PersonNode2 {
    public int number;
    public String name;
    public String nickName;
    public PersonNode2 next;
    public PersonNode2 pre;

    public PersonNode2(int number, String name, String nickName) {
        this.number = number;
        this.name = name;
        this.nickName = nickName;
    }

    public PersonNode2 getNext() {
        return next;
    }

    public PersonNode2 getPre() {
        return pre;
    }

    @Override
    public String toString() {
        return "PersonNode2{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +

                '}';
    }
}
