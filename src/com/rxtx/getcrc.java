package com.rxtx;

public class getcrc {
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] aa = {0x01,0x02,0x00,0x00,0x00,0x0C};
		byte[] bb = new byte[3];
		getcrc.get_crc16(aa, aa.length, bb);
		
		System.out.println(Integer.toHexString((int)bb[0] & 0x000000ff));
		System.out.println(Integer.toHexString((int)bb[1] & 0x000000ff));
		System.out.println(Integer.toHexString((int)bb[0]));
		printHexString(bb);
	}
	public static void printHexString( byte[] b) {  
		   for (int i = 0; i < b.length; i++) { 
		     String hex = Integer.toHexString(b[i] & 0xFF); 
		     if (hex.length() == 1) { 
		       hex = '0' + hex; 
		     } 
		     System.out.print(hex.toUpperCase() ); 
		   } 

		}
}
