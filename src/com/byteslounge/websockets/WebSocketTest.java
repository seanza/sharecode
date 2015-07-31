package com.byteslounge.websockets;
 
import java.io.IOException;
 








import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.action.LogSaver;
import com.rxtx.Modbus;
import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.sql.Getinfo;
@ServerEndpoint("/websocket")
public class WebSocketTest {
    //串行口波特率
    private final static int BAUD_RATE = 9600;
  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, InterruptedException {
	  String com=Getinfo.getcomname(1).toUpperCase();
      int[] di1=Getinfo.getcomdoor(1);
    System.out.println("Received: " + message);
    System.out.println("Received: " + com+di1[2]+di1[3]);
    //session.getBasicRemote().sendText("{type:'aa',text:'"+message+"'}");
    int sentMessages = 0;
    boolean b=Modbus.readInput(com,di1[2],di1[3]-1,1)[0];
	LogSaver ls = new LogSaver();
	String uname = "null";
	String authority = "!!!";
    while(sentMessages<1){
      Thread.sleep(3000);
      boolean[] a=Modbus.readInput(com,di1[2],di1[3]-1,1);
      if(a!=null){
      if(b==a[0]){
    	  
      }
      else if(a[0]==true){
    	  //写日志
			ls.saveinlog("opendoor", uname, authority);
            session.getBasicRemote().sendText("开门");
      }
      else{
    	//写日志
    	  ls.saveinlog("closedoor", uname, authority);
    	  session.getBasicRemote().sendText("关门");
      }
      b = a[0];
    }
    else{
    	session.getBasicRemote().sendText("通讯和位置设置问题"+ a[0]);
    }
    }
   
    // Send a final message to the client
    session.getBasicRemote().sendText("This is the last server message");
  }
   
  @OnOpen
  public void onOpen() {
    System.out.println("Client connected");
  }
 
  @OnClose
  public void onClose() {
    System.out.println("Connection closed");
  }
}