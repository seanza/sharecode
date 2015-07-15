package com.rxtx;

public class Aboutbyte {

	public static void main(String[] args) {
		Test a=new Test();
		byte[] b=onlight(2,2);
		String c="com4";
	    a.openSerialPortb(b,c);

	}
    public static byte[] onlight(int a,int b){        //打开指定行列的DO
    	byte[] b1={0x00,0x05,0x00,0x00,(byte) 0xFF,0x00,(byte) 0x98,0x35};
    	b=b-1;
    	Integer.toHexString(a);
    	Integer.toHexString(b);
        b1[0]=(byte)a;
        b1[3]=(byte)b;
    	byte[] bb = new byte[3];
		getcrc.get_crc16(b1, b1.length-2, bb);
		b1[6]=bb[0];
		b1[7]=bb[1];
    	return b1;
    }
    public static byte[] offlight(int a){                //关闭整行DO
    	byte[] b1={0x01,0x0F,0x00,0x00,0x00,0x0A,0x02,0x00,0x00,(byte) 0xE5,0x38};
       	Integer.toHexString(a);
        b1[0]=(byte)a;
    	byte[] bb = new byte[3];
		getcrc.get_crc16(b1, b1.length-2, bb);
		b1[9]=bb[0];
		b1[10]=bb[1];
    	return b1;
    }
    public static byte[] offsinglelight(int a,int b){        //关闭指定行列的DO
    	byte[] b1={0x00,0x05,0x00,0x00,0x00,0x00,(byte) 0x98,0x35};
    	b=b-1;
    	Integer.toHexString(a);
    	Integer.toHexString(b);
        b1[0]=(byte)a;
        b1[3]=(byte)b;
    	byte[] bb = new byte[3];
		getcrc.get_crc16(b1, b1.length-2, bb);
		b1[6]=bb[0];
		b1[7]=bb[1];
    	return b1;
    }
    public static byte[] getdi(int a){                          //得到指定行的所有DI
    	byte[] b1={0x01,0x02,0x00,0x00,0x00,0x0C,(byte) 0x78,0x0F};
       	Integer.toHexString(a);
        b1[0]=(byte)a;
    	byte[] bb = new byte[3];
		getcrc.get_crc16(b1, b1.length-2, bb);
		b1[6]=bb[0];
		b1[7]=bb[1];
    	return b1;
    }
    public static int get_crc16 (byte[] bufData, int buflen, byte[] pcrc)
	{
		int ret = 0;
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;
		int i, j;
		if (buflen == 0)
		{
			return ret;
		}
		for (i = 0; i < buflen; i++)
		{
			CRC ^= ((int)bufData[i] & 0x000000ff);
			for (j = 0; j < 8; j++)
			{
				if ((CRC & 0x00000001) != 0)
				{
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				}
				else
				{
					CRC >>= 1;
				}
			}
			//System.out.println(Integer.toHexString(CRC));
		}
		
		System.out.println(Integer.toHexString(CRC));
		pcrc[0] = (byte)(CRC & 0x00ff);
		pcrc[1] = (byte)(CRC >> 8);

		return ret;
	}
    public static String binaryString2hexString(String bString)
    {
    	if (bString == null || bString.equals("") || bString.length() % 8 != 0)
    		return null;
    	StringBuffer tmp = new StringBuffer();
    	int iTmp = 0;
    	for (int i = 0; i < bString.length(); i += 4)
    	{
    		iTmp = 0;
    		for (int j = 0; j < 4; j++)
    		{
    			iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
    		}
    		tmp.append(Integer.toHexString(iTmp));
    	}
    	return tmp.toString().toUpperCase();
    }
}
