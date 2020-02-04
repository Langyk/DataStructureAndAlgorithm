package hashtable;

import java.util.Hashtable;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/2 16:06
 * @Version 1.0
 */
public class HashTableDemo {
    public static void main(String[] args) {
        hashTable ht= new hashTable(7);
        Person person1=new Person(1,"Simon");
        Person person2=new Person(2,"Kavin");
        Person person3=new Person(3,"Snow");
        ht.add(person1);
        ht.add(person2);
        ht.add(person3);
        ht.list();
        ht.find(3);

    }

}
/**
 * 创建hashTable，管理多条链表
 */
class hashTable{
    private PersonLinkedList[] personLinkedListArray;
    private int size;
    public hashTable(int size){
        this.size=size;
        personLinkedListArray =new PersonLinkedList[size];
        //对每一个链表的头结点进行初始化
        for (int i=0;i<size;i++){
            personLinkedListArray[i]=new PersonLinkedList();
        }
    }
    //添加雇员
    public void add(Person person){
        int perLinkedListNO=hashFun(person.id);
        //将person添加到对应链表中
        personLinkedListArray[perLinkedListNO].add(person);
    }

    //查询
    public void find(int id){
        int perLinkedListNO=hashFun(id);
       Person person= personLinkedListArray[perLinkedListNO].findById(id);
       if(person!=null){
           System.out.println(person);
       }else {
           System.out.println("在哈希表中没有找到该people");
       }

    }

    //遍历所有的链表，遍历hashTable
    public void list(){
        for (int i=0;i<size;i++){
            personLinkedListArray[i].list(i);
        }
    }
    //编写散列函数
    public int hashFun(int id){
        return id%size;
    }
}

/**
 * 定义一个结点类
 */
class Person {
    public int id;
    public String name;
    public Person next;

    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 定义一个链表
 */
class PersonLinkedList {
    //定义一个头指针，指向第一Person结点
    private Person head;

    //1、添加
    public void add(Person person) {
        //如果是第一个雇员
        if (head == null) {
            head = person;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Person curPer=head;
        while (true){
            if(curPer.next==null){
                break;
            }
            curPer=curPer.next;
        }
        curPer.next=person;
    }

    //3、查询
    public Person findById(int id){
        if(head==null){
            return null;
        }
        Person curPerson=head;
        while (true){
            if(curPerson.id==id){
                break;
            }
            if(curPerson==null){
                break;
            }
            curPerson=curPerson.next;
        }
        return curPerson;
    }
    //4、删除
    public void deleteById(int id){
        if(head==null){
            System.out.println("该链表不存在");
        }
        Person curPerson=head;
        while (true){
            if(curPerson.id==id){
                head=curPerson.next;
                break;
            }
            if(curPerson.next.id==id){
                Person  p=curPerson.next;
                curPerson.next=p.next;
                break;
            }
            if(curPerson.next==null){
                break;
            }

        }


    }
    //2、遍历链表的雇员信息
    public void list(int no){
        if(head==null){
            System.out.println("第"+(no+1)+"链表为空");
            return;
        }
        System.out.println("当前链表的信息为：");
        Person curPer=head;
        while (true){
            System.out.printf("id=%d   name=%s\t",curPer.id,curPer.name);
            if(curPer.next==null){
                break;
            }
            curPer=curPer.next;
        }
        System.out.println();
    }
}