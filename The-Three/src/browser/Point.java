package browser;

public class Point {
	public Point(String i) {
		// TODO Auto-generated constructor stub
		String[] tmp=i.split(",");
		x=Double.valueOf(tmp[0]);
		y=Double.valueOf(tmp[1]);
	}
	public double x;
	public double y;
}
