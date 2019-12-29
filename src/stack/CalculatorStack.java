package stack;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/28 10:48
 * @Version 1.0
 */
public class CalculatorStack {
    public static void main(String[] args) {

        //构造两个栈，数字栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 opeStack = new ArrayStack2(10);
        //建立一个索引，扫描表达式的符号
        String expression = "70+2*6-4";
        int index = 0;
        //定义需要用到的变量
        int num1 = 0;
        int num2 = 0;
        int sum = 0;
        int oper=0;
        char ch=' '; //将每次扫描得到的字符保存到ch中
        String keepNum="";
        //取出表达式中的符号，并做运算
        while (true){
            //取出表达式中的字符
             ch=expression.substring(index,index+1).charAt(0);
            //判断ch是否为运算符，
            if (opeStack.isOperator(ch)){
                //判断符号栈中是否存在运算符
                if(!opeStack.isEmpty()){
                    //符号栈中有运算时，比较优先级
                    //若index指向的优先级小于栈顶的优先级，则将数字栈中弹出两个元素，符号栈中弹出栈顶元素，运算好放入数字栈
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.getOper())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = opeStack.pop();
                        sum = numStack.calculation(num2, num1, oper);
                        //把结果压入数字栈中
                        numStack.push(sum);
                        //把当前的符号符入符号栈
                        opeStack.push(ch);
                    }//若当前优先级大于栈顶优先级，直接入栈
                    else {
                        opeStack.push(ch);
                    }

                }else {
                    opeStack.push(ch);
                }



            }else {
                //取出的字符为数字,可能有多位,所以定义一个字符串拼接变量keepNum
                keepNum=keepNum+ch;
                //判断是否在表达式的末尾
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                    break;
                }else {
                    if (numStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {

                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
            }
            //判断index是否扫描到表达式的末尾
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //若表达式扫描完毕，就顺序的从数字栈和符号栈pop出响相应的数和符号，并运行
        while (true){
            //判断数字栈的大小
            if(opeStack.isEmpty()){
                 break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=opeStack.pop();
            sum=numStack.calculation(num2,num1,oper);
            numStack.push(sum);
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

class ArrayStack2 {
    private int top = -1; //定义指向栈顶指针,初始化为-1
    private int[] stack; //用数组模拟栈
    private int maxSize; //栈的容量

    //构造器
    public ArrayStack2(int maxSize) {
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

    //判断表达式中运算的优先级
    public int priority(int ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        }
        if (ch == '+' || ch == '-') {
            return 0;
        }
        return -1;
    }

    //判断表达式的符号是否为运算符
    public boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

    //表达式的计算方法
    public int calculation(int num2, int num1, int ch) {
        int sum = 0;
        switch (ch) {
            case '+':
                sum = num2 + num1;
                break;
            case '-':
                sum = num2 - num1;
                break;
            case '*':
                sum = num2 * num1;
                break;
            case '/':
                sum = num2 / num1;
                break;
            default:
                break;
        }

        return sum;
    }
    //查看符号栈中的栈顶运输
    public int getOper(){
        return stack[top];
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
