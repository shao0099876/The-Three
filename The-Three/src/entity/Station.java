package entity;

public class Station {
	public String stationName;
	private String stationAddr;
	private String stationGPS;
	public Station(String p1,String p2,String p3) {
		stationName=p1;
		stationAddr=p2;
		stationGPS=p3;
	}
	public String[] toStringArray() {
		String[] res=new String[3];
		res[0]=stationName;
		res[1]=stationAddr;
		res[2]=stationGPS;
		return res;
	}
}
