package com.rxtx;

import com.serotonin.io.serial.*;
import com.serotonin.modbus4j.*;
import com.serotonin.modbus4j.exception.*;
import com.serotonin.modbus4j.msg.*;

import java.util.*;

public class Modbus {
	 private final static int BAUD_RATE = 9600;
	 public static void main(String[] args) throws Exception {
		 Boolean a=getmodfirstdi("COM4",1,1,2);
         System.out.println(a);
	 }
     public static Boolean getmodfirstdi(String com, int slaveId, int start, int len) {
    	SerialParameters serialParameters = new SerialParameters();
    	 //设定MODBUS通讯的串行口
        serialParameters.setCommPortId(com);
 
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
    	ModbusMaster master=modbusFactory.createRtuMaster(serialParameters);
    	try {
			master.init();
		} catch (ModbusInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	
            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
 
            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                return (response.getBooleanData()[0]);
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        master.destroy();
		return null;
    }  
     public static Boolean readDiscreteInputTestint(ModbusMaster master, int slaveId, int start, int len) {
         try {
             ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
             ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
  
             if (response.isException())
                 System.out.println("Exception response: message=" + response.getExceptionMessage());
             else
                 return (response.getBooleanData()[0]);
         }
         catch (ModbusTransportException e) {
             e.printStackTrace();
         }
 		return null;
     }
}
