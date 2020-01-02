package stack;

import javax.naming.ldap.PagedResultsControl;
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
        /**
         * 中缀表达式转后缀表达式
         */
        String inSuffixExpression="1+((2+3)*4)-5";
        List list=getInSuffixExpression(inSuffixExpression);
        List<String> ls=inFixExpressionToSuffixExpression(list);
        System.out.println(ls);
        int value=calculate(ls);
        System.out.println(value);

//        String suffixExpression = "3 4 + 5 * 6 -";
//       List<String> list = getListString(suffixExpression);
//       int value=calculate(list);
//        System.out.println(value);

    }

    /**
     * 中缀表达式放入list中的方法
     */
    public static List<String> getInSuffixExpression(String suffixExpression){
        List<String> list=new ArrayList<>();
        int index=0;
        String str;
        char c;
        do{
            if((c=suffixExpression.charAt(index))<48||(c=suffixExpression.charAt(index))>57){
                list.add(""+c);
                index++;
            }
            else {
                str="";
                //判断是否为多位数
                if(index<suffixExpression.length()&&(c=suffixExpression.charAt(index))>48&&(c=suffixExpression.charAt(index))<57){
                    str+=c;
                    index++;
                }
                list.add(str);
            }

        }while (index<suffixExpression.length());
        return list;
    }

    /**
     * 中缀表达式转后缀表达式的方法InfixExpressionToSuffixExpression
     * 1+((2+3)*4-5) ===>1 2 3 + 4 * + 5 -
     */
    public static List<String> inFixExpressionToSuffixExpression(List<String> inFixExpression){
        Stack<String> s1=new Stack<>();
        List<String > s2=new ArrayList<>();

        for(String item:inFixExpression){
            //如果是一个数，直接加入S2
                if(item.matches("\\d+")){
                s2.add(item);
                }
                else if(item.equals("(")){
                    s1.push(item);
                }else if(item.equals(")")){
                    while (!s1.peek().equals("(")){
                        s2.add(s1.pop());
                    }
                    s1.pop();
                }else {
                    //当item的优先级小于等于s1栈的运算符，将s1栈的运算符弹出加入到s2中。
                    while (s1.size()!=0&&Operation.oper(item)<=Operation.oper(s1.peek())){
                            s2.add(s1.pop());
                    }
                    s1.push(item);
                }
        }
        //将栈s1中的数据放入到s2集合中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     *后缀表达式放入list中的方法
     */
    public static List<String> getListString(String suffixExpression) {
        //将字符串中的字符存入List数组中
        String[] rpn = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rpn.length; i++) {
            list.add(rpn[i]);
        }

        return list;
    }

    public static int calculate(List<String> list){
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
        return Integer.parseInt(stack.pop()) ;

    }


}
class  Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    public static int oper(String ch){
        int res=0;
        switch (ch){
            case "+":
                res=ADD;
                break;
            case "-":
                res=SUB;
                break;
            case "*":
                res=MUL;
                break;
            case "/":
                res=DIV;
                break;
            default:
                System.out.println("该符号不是运算符");
        }
        return res;
    }
}
