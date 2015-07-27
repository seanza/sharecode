package mypao;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;


/**
 * Example of how to take single picture.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class TakePictureExample {

	public static void main(String[] args) throws IOException {

		Dimension[] nonStandardResolutions = new Dimension[] {
    			WebcamResolution.PAL.getSize(),
    			WebcamResolution.HD720.getSize(),
    			new Dimension(2000, 1000),
    			new Dimension(1000, 500),
    		};
        Webcam webcam = Webcam.getDefault();
        webcam.setCustomViewSizes(nonStandardResolutions);
		webcam.setViewSize(WebcamResolution.HD720.getSize());
		webcam.open();
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd时间HH时mm分ss秒");
		String st= dateFormat.format(date); 
		BufferedImage image = webcam.getImage();
		ImageIO.write(image, "JPG", new File("D:\\photo\\日期"+st+".jpg"));
		webcam.close();
	}
}
