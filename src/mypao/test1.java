package mypao;

public class test1 {

	public static void main(String[] args) {
		int back=0;
		int sumrownum=9;
		String[] str={"00000111","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000"};
		String[] ret={"00000011","00000000","00000000","00000000","00000000","00000000","00000000","00000000","00000000"};
		//for(int i=0;i<sumrownum;i++){
		//  if(str[i].equals(ret[i])){
		//	  back++;
		//  }
			  
		//}
		System.out.println("监测串口返回back数据"+str[0]);
		str[0]=str[0].substring(0, 5)+"000";
		System.out.println("监测串口返回back数据"+str[0]);
	}

}
