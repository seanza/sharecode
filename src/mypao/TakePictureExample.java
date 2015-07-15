package mypao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;


/**
 * Example of how to take single picture.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class TakePictureExample {

	public static void main(String[] args) throws IOException {

		// get default webcam and open it
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		// get image
		BufferedImage image = webcam.getImage();
        
		// save image to PNG file
		ImageIO.write(image, "PNG", new File("D:\\照片"+dateString+".png"));
	}
}
