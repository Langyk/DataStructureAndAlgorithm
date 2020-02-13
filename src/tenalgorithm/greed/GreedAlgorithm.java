package tenalgorithm.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Simon Lang
 * @Date: 2020/2/13 11:03
 * @Version 1.0
 */
public class GreedAlgorithm {
    public static void main(String[] args){
        //创建广播电视台，放入到Map
        HashMap<String, HashSet<String>> broadcasts=new HashMap<String, HashSet<String>>();
        //将各个电视台放入到broadcastszh
        HashSet<String> hashSet1=new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2=new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3=new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4=new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5=new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到map
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);
        //创建一个allAreas，存放所有的地区
        HashSet<String> allAreas=new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList，存放选择电台的集合
        ArrayList<String> selects=new ArrayList<>();
        //定义一个临时的集合，在遍历过程中存放电台覆盖的地区和还没有覆盖地区的交集
        HashSet<String> tempSet=new HashSet<String>();
        //定义maxKey，保存在一次遍历过程中能够覆盖最大的未覆盖地区的电台key
        //如果maxKey，不为null，则会加入到selects
        String maxKey =null;
        while (allAreas.size()!=0){
            //每进行一次循环，
            maxKey=null;
            //遍历所有的broadcast，找出对应的key
            for (String key:broadcasts.keySet()){
                //没进行一次for
                tempSet.clear();
                //当前这key能够覆盖的地区
                HashSet<String> areas=broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas的交集，交集会付给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖的地区数量，比maxKey指向的地区还多，就需要重置maxKey
                if(tempSet.size()>0&&(maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey=key;

                }

            }
            //maxKey!=null,就应该将maxKey加入selects
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey的指向广播电台的覆盖地区。从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
