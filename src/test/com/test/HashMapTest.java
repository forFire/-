package com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HashMapTest {
	
  
  
  public static void main(String[] args) {
	
	  Map map = new HashMap();
	  map.put("1", "zg");
	  map.put("2", "zg");
	  
	  Set set1 = map.keySet();
	  
	  
	  
	  Set set = new HashSet();
	  
	  set.add("1");
	  set.add("2");
	  
	  Iterator t = set.iterator();
	  
	  while(t.hasNext()){
		  
		  System.out.println(t.next());  
	  }
	  
	  
	  
	  
	  
	  
  }
  
}
