package LinkedList;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/26 11:11
 * @Version 1.0
 */


/**
 * 问题描述：约瑟夫问题是一个非常著名的趣题，即由n个人坐成一圈，按顺时针由1开始给他们编号。
 * 然后由第一个人开始报数，数到m的人出局。现在需要求的是最后一个出局的人的编号。
 * 给定两个int n和m，代表游戏的人数。请返回最后一个出局的人的编号。
 *
 * 问题分析：约瑟夫问题可以认为是一个单向循环链表的问题，
 * 1、构建单向循环链表
 * 2、链表里的节点出队列，直到仅存在一个节点
 */
public class JosephusQuestion {
    public static void main(String[] args) {
        CycleSingleLinkedList cycleSingleLinkedList = new CycleSingleLinkedList();
        cycleSingleLinkedList.addByCircleLindList(5);
//        cycleSingleLinkedList.show(cycleSingleLinkedList.first);
        cycleSingleLinkedList.moveCycle(2,2,5);
    }
}

class CycleSingleLinkedList {
    //定义一个first节点
    Boy first = null;

    /**
     * 定义一个构造单向循环链表的方法
     */
    public void addByCircleLindList(int num) {
        if (num < 1) {
            System.out.println("num的值不正确");
        }
        //定义一个辅助节点cur
        Boy cur = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            //构造第一个有效节点
            if (i == 1) {
                first = boy;//第一个节点为first
                first.setNext(first);//仅有一个节点时，first指向first，构成闭环
                cur = first;//辅助变量指向first
            } else {
                cur.setNext(boy);//将新添加的节点设置为当前节点的后继节点
                boy.setNext(first);//新添加的节点与first构成闭环
                cur = boy;//将当前节点设置为新添加的节点
            }
        }
    }

    /**
     * 小孩出圈
     * @param no   从第几个小孩开始数
     * @param number  每次数几下
     * @param sum     一共有多少个小孩
     */
    public void moveCycle(int no,int number,int sum){
        //检验参数
        if(no>sum||sum<1||number<0){
            System.out.println("输入数据错误");
        }
        //定义辅助节点变量,使辅助节点变量指向最后一个节点，当辅助节点变量与first指向同一个节点时，则圈内只剩一个节点，其它节点全部出圈
        Boy helper=first;
        while (true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //确定从第几个节点开始
        for(int i=0;i<no-1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }

        while (true){
            if(first==helper){
                System.out.printf("最后一个节点时%d\n",first.getNumber());
                break;
            }
            //开始,每number下，出圈一次
            for(int j=0;j<number-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("%d出圈\n",first.getNumber());
            first=first.getNext();
            helper.setNext(first);
        }
    }

    /**
     * 遍历单向循环链表
     */
    public void show(Boy boy) {
        Boy cur = boy;
        if (cur == null) {
            System.out.println("该循环链表为空");
        }
        while (true) {
            System.out.println(cur);
            cur = cur.getNext();
            if (cur == first) {
                break;
            }
        }

    }
}

class Boy {
    private int number;
    private Boy next;

    public Boy(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "number=" + number +

                '}';
    }
}