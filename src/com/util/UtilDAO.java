package com.util;
import java.util.*;
import java.text.*;
public class UtilDAO {
   public static String genTime(){
	   Date cur= new Date();
	   SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
	   return simple.format(cur);
   }
   
   public static String genDate(){
	   Date cur= new Date();
	   SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
	   return simple.format(cur);
   }


	   public static String gen(Date cur){
		   SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");
		   return simple.format(cur);
	   }

	   public static String genDate(Date cur){
		   SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		   return simple.format(cur);
	   }
}
