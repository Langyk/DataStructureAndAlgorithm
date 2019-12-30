package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Simon Lang
 * @Date: 2019/12/29 19:55
 * @Version 1.0
 */
public class ReversePolandExpressionDemon {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 -";
        int value = getListString(suffixExpression);
        System.out.println(value);

    }

    public static int getListString(String suffixExpression) {
        //将字符串中的字符存入List数组中
        String[] rpn = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rpn.length; i++) {
            list.add(rpn[i]);
        }
        Stack<String> stack = new Stack();
        //将List数组中的字符放入栈中进行运算
        for (String element : list) {
            if (element.matches("\\d+")) {//匹配多位数
                stack.push(element);

            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (element.equals("+")) {
                    res = num1 + num2;
                } else if (element.equals("-")) {
                    res = num2 - num1;
                } else if (element.equals("*")) {
                    res = num1 + num2;
                } else if (element.equals("/")) {
                    res = num2 / num1;
                } else {
                    throw new RuntimeException("不识别的字符");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }


}
