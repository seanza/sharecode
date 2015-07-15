package mypao;

import java.math.BigInteger;

public class Binary2Hex
{
	public static void main(String[] args)
	{
		String bString = "06";
		String aaa=hexString2binaryString(bString);
		String des="1";
		int cnt=sumfind(aaa,des);
        System.out.println(cnt);
	}

	public static String hexString2binaryString(String hexString)  
	{  
	    if (hexString == null || hexString.length() % 2 != 0)  
	        return null;  
	    String bString = "", tmp;  
	    for (int i = 0; i < hexString.length(); i++)  
	    {  
	        tmp = "0000"  
	                + Integer.toBinaryString(Integer.parseInt(hexString  
	                        .substring(i, i + 1), 16));  
	        bString += tmp.substring(tmp.length() - 4);  
	    }  
	    return bString;  
	}  
	public static int sumfind(String aaa,String des){
		
		int cnt = 0;
        int offset = 0;
        while((offset = aaa.indexOf(des, offset)) != -1){
            offset = offset + des.length();
            cnt++;
        }
		return cnt;
		
	}
	
	
}
