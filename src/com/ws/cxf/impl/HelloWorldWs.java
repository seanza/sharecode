package com.ws.cxf.impl;

import javax.jws.WebService;

import com.ws.cxf.HelloWord;
@WebService(endpointInterface="com.ws.cxf.HelloWord",serviceName="HelloWorldWs")
public class HelloWorldWs implements HelloWord {

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return name +"ฤ๚บร";
	}

}
