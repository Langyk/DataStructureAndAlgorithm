package queue;

/**
 * @Author: Simon Lang
 * @Date: 2019/11/30 17:18
 * @Version 1.0
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue=new Queue(5);
        for (int i=1;i<=3;i++){
            queue.addQueue(i);
        }
        queue.show();
    }
}
class Queue{
    private int maxSize;
    private int front=0; //队列头部
    private int rear=0;  //队列尾部的元素的后一个位置
    private int[] arr;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    /**
     * 队列是否为空
     */
    public boolean isEmpety(){
        if(rear!=front){
            return true;
        }
        System.out.println("队列有空的位置");
        return false;
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull(){
        if(rear!=maxSize){
            return true;
        }
        System.out.println("队列已满~~~");
        return false;
    }

    /**
     * 添加到队列
     */
    public void addQueue(int n){
        if(this.isFull()){
            arr[rear]=n;
            rear++;
        }
    }
    /**
     * 出队列
     */

    public void goQueue(){
        if(this.isEmpety()){
            front++;
        }
    }
    /**
     * 打印队列
     */
    public void show(){
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

}
