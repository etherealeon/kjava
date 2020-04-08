import java.util.Scanner;

public class Lab1 {
		public static void inp(double[] mas) {
			Scanner scanner = new Scanner(System.in);
			//String input = scanner.useDelimiter("\\A").next();
			double x,y,z;
			System.out.print("Введите Координату x: ");
	        if (scanner.hasNextDouble()) {
	        	x = scanner.nextDouble();
	            mas[0] = x;
	        } else {
	            System.out.println("Некорректный ввод");
	            return;
	        }
	        System.out.print("Введите Координату y: ");
	        if (scanner.hasNextDouble()) {
	            y = scanner.nextDouble();
	            mas[1] = y;
	        } else {
	            System.out.println("Некорректный ввод");
	            return;
	        }
	        System.out.print("Введите Координату z: ");
	        if (scanner.hasNextDouble()) {
	            z = scanner.nextDouble();
	            mas[2] = z;
	        } else {
	            System.out.println("Некорректный ввод");
	            return;
	        }
	        System.out.println("Создана точка с координатами ("+  x + ";" + y + ";" + z +")");
		}
	
	public static double computeArea(Point3d a, Point3d b, Point3d c)
	{
		double lena = Math.abs(a.distanceTo(b));
		double lenb = Math.abs(b.distanceTo(c));
		double lenc = Math.abs(c.distanceTo(a)); 
		double p = (lena + lenb + lenc) / 2;
		return (Math.sqrt(p*(p - lena) * (p - lenb) * (p - lenc)));
	}

 	public static void main(String[] args)
	{
 		double[] mas = new double[3];
		inp(mas);
 		Point3d a = new Point3d(mas[0], mas[1], mas[2]);
 		inp(mas);
		Point3d b = new Point3d(mas[0], mas[1], mas[2]);
		inp (mas);
		Point3d c = new Point3d(mas[0], mas[1], mas[2]);
		/*System.out.println(a.compare(b));
		System.out.println(a.compare(c));
		System.out.println(c.compare(b));*/
		
		if (a.compare(b) || a.compare(c) || c.compare(b))
			System.out.println( "\nНекорректные координаты точек");
		else
			System.out.println( "\n Площадь треугольника, образованного точками = ");
			System.out.printf("%.2f",computeArea(a, b, c));

	}
}
