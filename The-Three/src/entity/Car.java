package entity;

public class Car {
	private String carNumber;
	private int people1Number;
	private int people2Number;
	private int routeNumber;
	public Car() {
		
	}
	public Car(String p1,int p2,int p3,int p4) {
		carNumber=p1;
		people1Number=p2;
		people2Number=p3;
		routeNumber=p4;
	}
	public String[] toStringArray() {
		// TODO Auto-generated method stub
		String[] res=new String[4];
		res[0]=carNumber;
		res[1]=Integer.toString(people1Number);
		res[2]=Integer.toString(people2Number);
		res[3]=Integer.toString(routeNumber);
		return res;
	}
	
}
