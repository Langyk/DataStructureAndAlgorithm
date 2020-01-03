package weekly;

/**
 * @Author: Simon Lang
 * @Date: 2020/1/3 18:34
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * * 例如We Are Happy变成
 * We%20Are%20Happy
 */
public class Week02Test01 {
    public static void main(String[] args) {
        String expression = "We are Happy";   //空格也是字符
        List<String> list1 = getString1(expression);
        System.out.println(list1);

    }

    /**
     * 方法一：
     */
    public static List<String> getString1(String expression) {
        List<String> list = new ArrayList<>();
        //定义一个索引
        int index = 0;
        while (true) {
            char ch = expression.substring(index, index + 1).charAt(0);
            if (isPace(ch)) {
                list.add("%20");
            }
            list.add(ch + "");
            index++;
            if (index > expression.length() - 1) {
                break;
            }
        }
        return list;
    }

    public static boolean isPace(int ch) {
        if (ch == ' ') {
            return true;
        }
        return false;
    }
}
