package entity;

public class Cargo {
	private int cargoNumber;
	private String start;
	private String end;
	private int routeNumber;
	public Cargo() {
		
	}
	public Cargo(int p1,String p2,String p3,int p4) {
		cargoNumber=p1;
		start=p2;
		end=p3;
		routeNumber=p4;
	}
	public String toTransString() {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append(cargoNumber);
		sb.append("#");
		sb.append(start);
		sb.append("#");
		sb.append(end);
		sb.append("#");
		sb.append(routeNumber);
		return null;
	}
}
