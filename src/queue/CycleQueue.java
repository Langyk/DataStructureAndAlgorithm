package queue;

/**
 * @Author: Simon Lang
 * @Date: 2019/11/30 17:19
 * @Version 1.0
 */
public class CycleQueue {
    public static void main(String[] args){
        CQueue cQueue=new CQueue(11);
        for (int i=1;i<=5;i++){
            cQueue.addQueue(i);
        }
        cQueue.show();
    }
}
class CQueue {
    private int head = 0;      //指向第一个元素
    private int tail = 0; //执行最后一个元素的后一个位置
    private int maxSize = 11;
    private int[] CQ;

    public CQueue(int maxSize) {
        maxSize = this.maxSize;
        CQ = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpety() {
        if (head == tail) {
            System.out.println("队列还有位置~~~");
            return false;
        }

        return true;
    }

    //判断队列是否满
    public boolean isFull() {
        if ((tail + 1) % maxSize == head) {
            System.out.println("队列已满~~~");

            return false;
        }

        return true;
    }

    //加入数据
    public void addQueue(int n) {
        if (isFull()) {
            CQ[tail] = n;
            tail = (tail + 1) % maxSize;
            ;
        }

    }

    //取出数据
    public void removeQueue() {
        if (isEmpety()) {
            head = (head + 1) % maxSize;
        }

    }

    //显示数组中剩余的数据
    public void show() {
        for (int i = head; i < head+this.size(); i++) {
            System.out.println(i%maxSize);
        }


    }
    //当前数据中有效的个数

    public int size() {
        return (tail+maxSize-head)%maxSize;
    }

}
