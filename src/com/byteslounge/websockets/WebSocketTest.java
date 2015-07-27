package com.byteslounge.websockets;
 
import java.io.IOException;
 




import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.rxtx.Modbus;
import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
@ServerEndpoint("/websocket")
public class WebSocketTest {
	private final static int SLAVE_ADDRESS=1;
    //串行口波特率
    private final static int BAUD_RATE = 9600;
  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, InterruptedException {
	  SerialParameters serialParameters = new SerialParameters();
      //设定MODBUS通讯的串行口
      serialParameters.setCommPortId("COM4");
      //设定成无奇偶校验
      serialParameters.setParity(0);
      //设定成数据位是8位
      serialParameters.setDataBits(8);
      //设定为1个停止位
      serialParameters.setStopBits(1);
      serialParameters.setPortOwnerName("Numb nuts");
      //串行口上的波特率
      serialParameters.setBaudRate(BAUD_RATE);
      ModbusFactory modbusFactory = new ModbusFactory();
      ModbusMaster master = modbusFactory.createRtuMaster(serialParameters);
    // Print the client message for testing purposes
    System.out.println("Received: " + message);
   
    // Send the first message to the client
    session.getBasicRemote().sendText("{type:'aa',text:'"+message+"'}");
    // Send 3 messages to the client every 5 seconds
    int sentMessages = 0;
    Boolean a = null;
    while(sentMessages < 3){
      Thread.sleep(5000);
      try {
          master.init();
          a=Modbus.readDiscreteInputTestint(master,1,1,2);
          System.out.println(a);
      } catch (ModbusInitException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      finally {
          master.destroy();
      }
      session.getBasicRemote().
        sendText("This is an intermediate server message. Count: "
          + a);
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