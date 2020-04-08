import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;

public class FractalExplorer {
	
	  //Целое число «размер экрана», которое является шириной и высотой отображения в пикселях
    private int displaySize;
   // Ссылка JImageDisplay, для обновления отображения в разных методах в
    //процессе вычисления фрактала.
    
    private JImageDisplay display;
//Объект FractalGenerator
    
    private FractalGenerator fractal;
//Объект Rectangle2D.Double, указывающий диапазона
    //комплексной плоскости, которая выводится на экран.
    
    private Rectangle2D.Double range;
//конструктор, который принимает значение размера   отображения в качестве аргумента, 
//  затем сохраняет это значение в 
 //   соответствующем поле,а также инициализирует объекты диапазона и фрактального генератора
   
    public FractalExplorer(int size) {
    displaySize = size;
    fractal = new Mandelbrot();
    range = new Rectangle2D.Double();
    fractal.getInitialRange(range);
    display = new JImageDisplay(displaySize, displaySize);
    
}

//  инициализирует графический интерфейс Swing
public void createAndShowGUI()
{
    display.setLayout(new BorderLayout());
    JFrame myFrame = new JFrame("Fractal Explorer");
    myFrame.add(display, BorderLayout.CENTER);
    
   // кнопка сброса
    JButton resetButton = new JButton("Reset");
    myFrame.add(resetButton, BorderLayout.SOUTH);
    ButtonHandler resetHandler = new ButtonHandler();
    resetButton.addActionListener(resetHandler);
    
    MouseHandler click = new MouseHandler();
    display.addMouseListener(click);

    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    myFrame.pack();
    myFrame.setVisible(true);
    myFrame.setResizable(false);
    
}

//метод должен циклически проходить через каждый пиксель в отображении 
private void drawFractal()
{
    /** Loop through every pixel in the display **/
    for (int x=0; x<displaySize; x++){
        for (int y=0; y<displaySize; y++){
            double xCoord = FractalGenerator.getCoord(range.x,
            range.x + range.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(range.y,
            range.y + range.height, displaySize, y);

            int iter = fractal.numIterations(xCoord, yCoord);
            
            if (iter == -1){
                display.drawPixel(x, y, 0);
            }
            else {
            	float hue = 0.7f + (float) iter / 200f;
                int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
            
                display.drawPixel(x, y, rgbColor);
            }
            
        }
    }
    display.repaint();
}



//Создайте внутренний класс для обработки событий 
//java.awt.event.ActionListener от кнопки сброса
 public class ButtonHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e)
    {
		//code that reacts to the action... 
        String command = e.getActionCommand();
        
        if (command.equals("Reset")) {
            fractal.getInitialRange(range);
            drawFractal();
        } } } 
 private  class MouseHandler extends MouseAdapter
 {
     @Override
     public void mouseClicked (MouseEvent e)
     {
         int x = e.getX();
         double xCoord = fractal.getCoord(range.x,
         range.x + range.width, displaySize, x);
         
         int y = e.getY();
         double yCoord = fractal.getCoord(range.y,
         range.y + range.height, displaySize, y);
         

         fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

         drawFractal();
     }
 }

 public static void main(String[] args) {
	 // 800 не помещается в экран((
     FractalExplorer displayExplorer = new FractalExplorer(650);
     displayExplorer.createAndShowGUI();
     displayExplorer.drawFractal();
 }
}

