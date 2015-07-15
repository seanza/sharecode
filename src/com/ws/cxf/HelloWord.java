package com.ws.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWord {
   String sayHi(String name);
}
