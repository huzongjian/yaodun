package com.springmvc.test;

import java.util.ArrayList;
import java.util.List;

import java.util.*;
/*
该示例为把集合中把同一箱子（箱号和名字相同即认定为同一箱子）根据型号的进行统计输出
例如：
   箱号    箱型   名字
    1       20     箱1
     2       40     箱2
     2       20     箱2
     2       40     箱2

   要求处理成的结果：
   箱号   名字  20箱型数量  40箱型数量
   1      箱1      1            0
    2      箱2      2            1

*/
public class EquComp{
    public static void main(String[] args){
           List<Box> hs = new ArrayList<Box>();
           hs.add(new Box("1","箱1","20"));
           hs.add(new Box("3","箱2","40"));
           hs.add(new Box("2","箱1","40"));
		   hs.add(new Box("4","箱2","20"));
		   hs.add(new Box("2","箱2","40"));
           hs.add(new Box("2","箱2","40"));
           List<Box> newStats = new EquComp().getBox(hs);
           System.out.println("箱号--------名字--------20箱型数量--------40箱型数量");
           System.err.println(hs.get(4).equals(hs.get(3)));
           for(Box s:newStats){
                System.out.println(s.getBoxNo()+"------------"+ s.getBoxName()+"------------"+s.getCount20()+"----------------"+ s.getCount40());
           }

    }

    public List<Box> getBox(List<Box> hs){
		List<Box> boxList = new ArrayList<Box>();
		boolean flag = false;
		String boxType = "";
		for (Box sta : hs) {
			flag = false;
			boxType = sta.getBoxType();
			String x = "";
			for (Box n : boxList) {
				if (n.equals(sta)) {
					x =n.getBoxType();
					if (n.getBoxType().equals(boxType)) {
						n.setCount20(n.getCount20()+1);
					} else   {
						n.setCount20(n.getCount20()+1);
					}
					flag = true;
					break;
				}
			}
			System.out.println(x);
			if (!flag) {
				if (x.equals(boxType)) {
					sta.setCount20(1);
				} else{
					sta.setCount40(1);
				}
				boxList.add(sta);
			}

	   }
	   //通过程序进行排序
	   Collections.sort(boxList,new ComparableYS());
	   return boxList;
    }

}


class ComparableYS implements Comparator{

    public int compare(Object o1, Object o2){
         Box s1 = (Box)o1;
         Box s2 = (Box)o2;
         //排序顺序 boxNo,boxName
         if(!s1.getBoxNo().equals(s2.getBoxNo())){
               return s1.getBoxNo().compareTo(s2.getBoxNo());
         }else if(!s1.getBoxName().equals(s2.getBoxName())){
               return s1.getBoxName().compareTo(s2.getBoxName());
         }else{
               return 0;
         }
    }

    public boolean equals(Object o){
         return false;
    }

}

class Box{
   private String boxNo;//箱号
   private String boxType;  //箱型
   private String boxName;//名字
   private int count_20;//箱型20数量
   private int count_40;//箱型40数量

   public void setCount40(int count_40){
        this.count_40 = count_40;
   }
   public void setCount20(int count_20){
       this.count_20 = count_20;
   }
   public int getCount20(){
       return count_20;
   }
   public int getCount40(){
       return count_40;
   }
   public Box(String boxNo,String boxName,String boxType){
       this.boxNo = boxNo;
       this.boxName = boxName;
       this.boxType = boxType;
   }
     public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }
    //判断是否是同一个箱子
    public boolean equals(Object o){
        Box s = (Box)o;
        return s.getBoxNo().equals(boxNo)&& s.getBoxName().equals(boxName)?true:false;
   }
}