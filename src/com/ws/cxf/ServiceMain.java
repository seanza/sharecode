package com.ws.cxf;



import javax.xml.ws.Endpoint;

import com.ws.cxf.impl.HelloWorldWs;

public class ServiceMain {

	public static void main(String[] args) {
		HelloWord hw=new HelloWorldWs();
		// TODO Auto-generated method stub
       Endpoint.publish("http://127.0.0.1:8088/www",hw);
       System.out.println("sssss");
	}

}
