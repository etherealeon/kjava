
public class Point3d {
	//координата X **/
	private double xCoord;
	//координата Y **/
	private double yCoord;
	private double zCoord;
	//Конструктор инициализации **/
	public Point3d(double x,  double y, double z)
	{
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}
	//Конструктор по умолчанию. **/
	public Point3d ()
	{
		//Вызовите конструктор с двумя параметрами и определите источник.
		this(0.0, 0.0, 0.0);
	}
	//Возвращение координаты X **/
	public double getX () {
	return xCoord;
	}
	//Возвращение координаты Y **/
	public double getY () {
	return yCoord;
	}
	public double getZ ()
	{
		return zCoord;
	} 
	//Установка значения координаты X. **/
	public void setX ( double val) {
	xCoord = val;
	}
	//Установка значения координаты Y. **/
	public void setY ( double val) {
	yCoord = val;
	}
	public void  setZ ( double val)
	{
		zCoord = val;
	}
	public double distanceTo (Point3d b)
	{
		double x1 = this.getX();
		double x2 = b.getX();
		double y1 = this.getY();
		double y2 = b.getY();
		double z1 = this.getZ();
		double z2 = b.getZ();
		return Math.round((Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2)))*100.0)/100.0;
	}
	public boolean compare(Point3d b)
	{
		return (this.getX() == b.getX() && this.getY() == b.getY() && this.getZ() == b.getZ());
	}
	public static void main(String[] args)
	{
	}
}
