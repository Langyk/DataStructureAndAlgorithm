package stack;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/26 18:57
 * @Version 1.0
 */

/**
 * 描述：用数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        arrayStack.push(5);
        arrayStack.push(4);
        arrayStack.push(3);
        arrayStack.push(2);
        arrayStack.push(1);
        arrayStack.pop();
        arrayStack.push(1);
        arrayStack.show();
    }
}

class ArrayStack {
        private int top = -1; //定义指向栈顶指针,初始化为-1
        private int[] stack; //用数组模拟栈
        private int maxSize; //栈的容量

        //构造器
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        //栈满
        public boolean isFull() {
            return top == maxSize - 1;
        }

        //栈空
        public boolean isEmpty() {
            return top == -1;
        }

        //入栈
        public void push(int value) {
            if (isFull()) {
                System.out.println("栈满，无法添加元素");
            }

            stack[++top] = value;
        }

        //出栈
        public int pop() {
            if (isEmpty()) {
                System.out.println("栈空，无法输出元素");
            }
            int value = stack[top];
            top--;
            return value;
        }

        //输出栈里面的元素，从栈顶开始打印
        public void show() {
            if (isEmpty()) {
                System.out.println("栈为空");
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("第%d个元素为%d\n", i, stack[i]);
            }
        }


}

