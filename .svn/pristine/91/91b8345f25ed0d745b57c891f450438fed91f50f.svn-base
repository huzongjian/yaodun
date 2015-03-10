package com.springmvc.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		
		List list = new ArrayList(); 
		list.add("aaaaa");  
		
		
		for (int i = 0; i < list.size(); i++) {
			String value = (String)list.get(i);
			int count = 0;    
			for (int j = 0; j < list.size(); j++) {
				if (value.equals(list.get(j))) { 
					count++;         
					}   
				}   
			if (count<=1) {
                  list.remove(value);   
					}   
			count=0;  
					}  
		System.out.println(list.isEmpty()); }
	 
	
}
