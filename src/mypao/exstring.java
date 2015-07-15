package mypao;

import java.util.Date;

public class exstring {
	public static void main(String[] args)
	{
		//String bString = "00000101aaa";
		//System.out.println(reverse(bString));
		String c="0F0U";
		String d="0F";
		int s=c.indexOf(",");
		String[] stringA= c.split(",");
		System.out.println(s);
		//System.out.println(date.toString());
	}
	
	public static String reverse(String str) {	
		return new StringBuilder(str).reverse().toString().toUpperCase();
	}
   public static int aaaa(String a,String b){
	   if(a.equals(b)){
	        return 1;
        }
        else{
    	   return 0;
        }
	   
	   
	   
   }
}
