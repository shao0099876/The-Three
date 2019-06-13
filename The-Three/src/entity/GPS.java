package entity;

public class GPS {
	private double x;
	private double y;
	public GPS(double p1,double p2) {
		x=p1;
		y=p2;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(x);
		sb.append(",");
		sb.append(y);
		return sb.toString();
	}
}
