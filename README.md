# 数据结构与算法

​		外修语言，内修算法，数据结构+算法是一个程序运行的保障，数据结构指的是数据的组成方式，如数组、数和链表等。数据结构可以分为线性数据结构和非线性数据结构，线性结构的特点是数据元素之间存在一一对应的关系，存储方式可以分为顺序表和链表，两者最大的不同在于存储的地址是否连续，典型的线性结构为<u>数组、链表、队列和栈</u>。非线性结构的特点是数据元素之间不存在一一对应的关系，典型的非线性结构有<u>二维数组、多维数组、广义表和数结构</u>

## 一、数组

### 1、稀疏数组

​		若一个二维数组中的元素存在很多0值或者无效值，可以将这个二维数组压缩为稀疏数组（去除0值和无效值）。可以应用在底图、棋盘等二维场景中。

![](数据结构与算法.assets/稀疏数组.PNG)

1.1二维数组转稀疏数组

①遍历原始的二维数组，得到有效的数据个数sum

②构造稀疏数组，第一行原始数组的行、列和有效数据的个数，其余行为有效值的行列坐标和存储的值

```java
sparseArr[sum+1][3]
```

1.2稀疏数组转二维数组

①取出稀疏数组的第一行，构造二维数组

②取出稀疏数组的其余行，为对应的二维数组赋值。

### 2、队列（数组实现）

队列如同现实中的排队一样，它的特点是先入先出，是一种有序列表，可有由数组或者是链表构成。

2.1数组模拟队列

![image-20191130124418217](数据结构与算法.assets/image-20191130124418217.png)

队列的输入输出分别队列的后、前端。对应的处理方法为加入数据和输出数据。(其前提是插入元素的个数要小于数组容量的个数，否则会发生越界错误，<u>因为tail是指向队尾的后一个位置</u>)

* 构造队列
* 判断队列是否为空 head !=tail
* 队列是否满 tail !=maxSize
* 加入队列   (先加入，后tail++)
* 退出队列 

对用的属性为maxSize、head（队头）、tail（队尾的后一个元素位置）

2.2环形队列

基本数组模拟队列会导致数组不能复用，所以优化方法是让数组构成环形队列。

![image-20191130143216719](数据结构与算法.assets/image-20191130143216719.png)

环形队列的基本思想是首尾相连。

* 构造队列

* 判断队列是否有空位置   head！=tail

* 判断队列是否满 (tail+1)%maxSize==head

* 加入队列，更新队头(tail+1)%maxSize

* 退出队列，更新队尾(head+1)%maxSize

* 队列长度（tail+maxSize-head）%maxSize

* 队列的索引变为 ：

  ```java
  for(i=head;i<head+(tail+maxSize-head)%maxSize;i++){
      System.out.println(arr[i%maxSize])
  }
  ```

## 二、链表(Linked List)

链表是以节点（node）存储的链式存储结构，一个node包含一个data域（存放数据）和一个next域（存放下一个node的指针），链表的各个节点不一定是连续的，它可以分为带头结点和不带头结点。头结点仅包含next域。

### 1、单向链表

1.1顺序插入

* 创建节点类，包含了data和next，next指向下一个节点对象

  >```java
  > class PersonNode {
  >    public int number;
  >    public String name;
  >    public String nickName;
  >    public PersonNode next;
  >
  >    public PersonNode(int number, String name, String nickName) {
  >        this.number = number;
  >        this.name = name;
  >        this.nickName = nickName;
  >    }
  >
  >    @Override
  >    public String toString() {
  >        return "PersonNode{" +
  >                "number=" + number +
  >                ", name='" + name + '\'' +
  >                ", nickName='" + nickName + '\'' +
  >                '}';
  >    }
  >}
  >```

* 创建单向链表类，里面包含了<u>增删改查的方法</u>

* 在链表类创建head Node，头节点的next指向第一个节点

* 实现增删改查的方法

![image-20191201182826935](数据结构与算法.assets/image-20191201182826935.png)

* 在链表中增加一个节点

> 确定要插入的节点位置，循环遍历到该位置节点的上一个节点。	
>
> ```C
> S->next=P->next
> P->next=S
> ```

* 在链表中删除一个节点

> 确定删除的位置，循环遍历到该位置节点的上一个节点
>
> ```C
> P->next=p->next->next
> ```

* 遍历链表

  ```java
   PersonNode temp = headNode;
          while (true) {
              if (temp.next == null) {
                  break;
              }
              temp = temp.next;
              System.out.println(temp);
          }
  ```

* 修改链表中的一个节点信息

> 确定要修改节点的位置，先遍历找到该节点，修改该节点的信息。

### 2、双向链表

双向链表是在单向链表的基础上加入pre指针，因此支持双向的增删改查。

>```java
> 	public int number;
>    public String name;
>    public String nickName;
>    public PersonNode next;
>    public PersonNode pre;
>```

* 双向链表的遍历

>双向链表的遍历与单向链表相同，可以按照两个方向遍历。

* 双向链表的添加

>1、在末尾添加
>
>![](数据结构与算法.assets/双向链表的末尾添加-1577283213037.PNG)
>
>在双向链表的末尾添加C节点（定位到节点B）
>
>```java
>B->next=C
>C->pre=B
>```
>
>2、在两节点间添加
>
>![](数据结构与算法.assets/双向链表中间添加.PNG)
>
>在节点B和节点C间添加D（程序定位到节点D），
>
>顺序：**先搞定D的前驱和后继节点，再搞定C的节点的前驱和B的后继节点。**
>
>```java
>D->pre=B
>D->next=C
>C->pre=D
>B->next=D
>```

* 双向链表的修改

>同单链表修改相同

* 双向链表的删除

>1、在链表尾部删除（定位到节点P（B））
>
>![](数据结构与算法.assets/双向链表末尾删除.PNG)
>
>2、在两节点间删除（定位到节点S（D））
>
>![](数据结构与算法.assets/双向链表中间删除.PNG)

