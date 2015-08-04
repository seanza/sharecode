package mypao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bb {
    public static void main(String[] args) {
            // TODO Auto-generated method stub    
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
    	//sdfdate.format(d);
		Date date1;
		try {
			date1 = df.parse("2016-02-02");

			System.out.println("时间"+date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

