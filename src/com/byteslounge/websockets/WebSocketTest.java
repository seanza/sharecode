package com.byteslounge.websockets;
 
import java.io.IOException;
 







import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.rxtx.Aboutbyte;
import com.rxtx.Modbus;
import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.sql.Getinfo;
@ServerEndpoint("/websocket")
public class WebSocketTest {
	private final static int SLAVE_ADDRESS=1;
    //串行口波特率
    private final static int BAUD_RATE = 9600;
  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, InterruptedException {
	  String com=Getinfo.getcomname(1);
	  com=com.toUpperCase();

      int[] di1=Getinfo.getcomdoor(1);
    // Print the client message for testing purposes
    System.out.println("Received: " + message);
    System.out.println("Received: " + com+di1[2]+di1[3]);
    //session.getBasicRemote().sendText("{type:'aa',text:'"+message+"'}");
    int sentMessages = 0;
    Boolean a = null,b = null;
    while(sentMessages<1){
      Thread.sleep(5000);
      try {
          byte[] door=Aboutbyte.getdi(di1[2]);
          System.out.println(a);
      } catch (ModbusInitException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      finally {
          master.destroy();
      }
      if(b==a){
    	  
      }
      else if(a==true){
    	  //写日志
      session.getBasicRemote().
        sendText("开门"
          + a);
      }
      else{
    	//写日志
    	  session.getBasicRemote().
          sendText("关门"
            + a);
      }
      b = a;
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