package tenalgorithm.kmp;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/12 15:24
 * @Version 1.0
 */
public class ViolencrMatch {
    public static void main(String[] args){
        String arr1="abcderghjkkkkkkk";
        String  arr2="bcder";
    }
    public static int violenceMatch(String arr1,String arr2){
        char[] s1=arr1.toCharArray();
        char[] s2=arr2.toCharArray();

        int len1=s1.length;
        int len2=s2.length;

        int i=0;
        int j=0;
        while (i<len1&&j<len2){
            if (s1[i]==s2[j]){
                i++;
                j++;
            }else {//若果没有匹配成功
                i=i-(j-1);
                j=0;
            }
        }
        //判断是否匹配成功
        if(j==len2){
            return i-j;
        }else {
            return -1;
        }

    }
}
