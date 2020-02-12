package tenalgorithm.kmp;

import java.util.Arrays;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/12 17:58
 * @Version 1.0
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next=kmpNext("ABCDABD");
        System.out.println("next="+ Arrays.toString(next));
        int index=kmpSerach(str1,str2,next);
        System.out.println(index);
    }
        //kmp查找算法
        public static int kmpSerach(String str1,String str2,int[] next){
        //遍历
        for(int i=0,j=0;i<str1.length();i++){
            //需要处理str1.charAt(i)!= str2.charAt(j),去调整j的大小
            while (j>0 && str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){//
                return i-j+1;
            }
        }
        return -1;
    }
    //获取到一个字符串（子串）的部分值匹配表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next =new int[dest.length()];
        next[0]=0;//如果字符串长度为1部分匹配值就是0
        for(int i=1,j=0;i<dest.length();i++){
            //当dest.chatAt(i)!=des.cahrAt(j),我们需要从next[j-1]获取新的j
            //直到我们发现有dest.chatAt(i)==des.cahrAt(j)成立才退出
            //kmp算法的核心核心点
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                    j=next[j-1];
            }
            //当dest.charAt(i)==dest.charAt(j)满足时，部分匹配值+1
            if(dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
