package com.rxtx;
import com.serotonin.io.serial.*;
import com.serotonin.modbus4j.*;
import com.serotonin.modbus4j.exception.*;
import com.serotonin.modbus4j.msg.*;

import java.util.*;
public class modbustest {
	    //设定MODBUS网络上从站地址
		private final static int SLAVE_ADDRESS = 10;
		// 串行波特率
		private final static int BAUD_RATE = 9600;

		public static void main(String[] args) {
			SerialParameters serialParameters = new SerialParameters();
			// 设定MODBUS通讯的串行口
			serialParameters.setCommPortId("COM4");
			// 设定成无奇偶校验
			serialParameters.setParity(0);
			// 设定成数据位是8位
			serialParameters.setDataBits(8);
			// 设定为1个停止位
			serialParameters.setStopBits(1);
			// 设定端口名称
			serialParameters.setPortOwnerName("Numb nuts");
			// 设定端口波特率
			serialParameters.setBaudRate(BAUD_RATE);

			// 创建ModbusFactory工厂实例
			ModbusFactory modbusFactory = new ModbusFactory();
			// 创建ModbusMaster实例
			ModbusMaster master = modbusFactory.createRtuMaster(serialParameters);

			// 初始化
			try {
				master.init();
				//boolean[] c={true,true,true};
				//writeCoilTest(master,SLAVE_ADDRESS,0,c); 
				//readHoldingRegistersTest(master,SLAVE_ADDRESS,0,2);
				//readCoilsTest(master,SLAVE_ADDRESS,0,4); 
				readDiscreteInputTest(master,3,0,8);

			} catch (ModbusInitException e) {
				e.printStackTrace();
			} finally {
				master.destroy();
			}
			
		}
		
		/**
		* 读开关量型的输入信号
		* @param master 主站
		* @param slaveId 从站地址
		* @param start 起始偏移量
		* @param len 待读的开关量的个数
		*/
		private static void readDiscreteInputTest(ModbusMaster master, int slaveId, int start, int len) {
		try {
			ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
			ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
			if (response.isException())
				System.out.println("Exception response: message=" + response.getExceptionMessage());
			else
				System.out.println(Arrays.toString(response.getBooleanData()));
			    boolean[] b=response.getBooleanData();
			    String a="";
			    for(int i = 0; i < b.length; i++){
			    String s = (b[i]) ? "1" : "0";
			    a=a+s;
			    }
			    System.out.println(a);
			}
			catch (ModbusTransportException e) {
				e.printStackTrace();
			}
		}
		private static void readCoilsTest(ModbusMaster master, int slaveId, int start, int len) {
			try {
				ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
				ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);
				if (response.isException())
					System.out.println("Exception response: message=" + response.getExceptionMessage());
				else
					System.out.println(Arrays.toString(response.getBooleanData()));
				}
				catch (ModbusTransportException e) {
					e.printStackTrace();
				}
			}
		
		
		/**
	     * 读保持寄存器上的内容
	     * @param master 主站
	     * @param slaveId 从站地址
	     * @param start 起始地址的偏移量
	     * @param len 待读寄存器的个数
	     */
		private static void readHoldingRegistersTest(ModbusMaster master,int slaveId, int start, int len) {
			try {
				ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(
						slaveId, start, len);
				ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
						.send(request);
				if (response.isException()) {
					System.out.println("Exception response: message="
							+ response.getExceptionMessage());
				} else {
					System.out.println(Arrays.toString(response.getShortData()));
					short[] list = response.getShortData();
					for(int i = 0; i < list.length; i++){
						System.out.print(list[i] + " ");
					}
				}
			} catch (ModbusTransportException e) {
				e.printStackTrace();
			}
		}
		
		
		
		 /**
	     * 批量写数据到保持寄存器
	     * @param master 主站
	     * @param slaveId 从站地址
	     * @param start 起始地址的偏移量
	     * @param values 待写数据
	     */
	    public static void writeRegistersTest(ModbusMaster master, int slaveId, int start, short[] values) {
	        try {
	            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
	            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);
	            if (response.isException()){
	                System.out.println("Exception response: message=" + response.getExceptionMessage());
	            }
	            else {
	                System.out.println("Success");
	            }
	        }
	        catch (ModbusTransportException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void writeCoilTest(ModbusMaster master, int slaveId, int start, boolean[] values) {
	        try {
	            WriteCoilsRequest request = new WriteCoilsRequest(slaveId, start, values);
	            WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);
	            if (response.isException()){
	                System.out.println("Exception response: message=" + response.getExceptionMessage());
	            }
	            else {
	                System.out.println("Success");
	            }
	        }
	        catch (ModbusTransportException e) {
	            e.printStackTrace();
	        }
	    }
	}