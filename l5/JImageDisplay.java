import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;


public class JImageDisplay extends JComponent {
	 private BufferedImage img;
	 public BufferedImage getImage() {
	        return img;
	    }
// конструктор класса
	 public JImageDisplay(int x, int y) {
		 img = new BufferedImage(x,y,TYPE_INT_RGB);
		 setPreferredSize(new Dimension(x, y));
	 }
	 // код для отрисовки
	 public void paintComponent (Graphics g){
		 g.drawImage (img, 0, 0, img.getWidth(), img.getHeight(), null);
	 }
	 // метод, который устанавливает все пиксели изображения в черный цвет
	 public void clearImage (){
		 for (int x = 0; x < img.getWidth(); x++) {
			    for (int y = 0; y < img.getHeight(); y++) {
			       img.setRGB(x, y, 0);
			    }
		 }
	    }
	 // метод, который устанавливает пиксель в определенный цвет
	 public void drawPixel (int x, int y, int rgbColor) {
		 img.setRGB(x, y, rgbColor);
	 }

}
