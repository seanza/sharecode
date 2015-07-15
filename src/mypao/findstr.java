package mypao;

public class findstr {

	public static void main(String[] args) {

	        String str = "divabcdivbbdivcdivdde",
	               des = "div";
	         
	        int cnt = 0;
	        int offset = 0;
	        while((offset = str.indexOf(des, offset)) != -1){
	            offset = offset + des.length();
	            cnt++;
	        }
	        System.out.println(cnt);
	}

}
