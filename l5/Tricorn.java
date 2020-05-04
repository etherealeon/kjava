import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
		  public static final int MAX_ITERATIONS = 2000;
		  // метод позволяет генератору фракталов определить наиболее «интересную»
		  //область комплексной плоскости для конкретного фрактала 
		  public void getInitialRange(Rectangle2D.Double range) {
		        range.x=-2;
		        range.y=-2;
		        range.height=4;
		        range.width=4;
		    }
		 // реализует итеративную функцию для фрактала Трикорна
		    public int numIterations(double x, double y) {
		        double re= 0;
		        double im= 0;
		        int i=0;
		        while ((i<MAX_ITERATIONS) && ((re*re+im*im)<4)){ 
		        double nxtRe = re*re- im*im +x;
		        double nxtIm = -2 * re *im +y;
		        re = nxtRe;
		        im = nxtIm;
		        i += 1; }
		        if (i == MAX_ITERATIONS)
		        	return -1;
		        return i;
		    }
		        

		       
		    public String toString() {
		        return "Tricorn";
		    }
		}
