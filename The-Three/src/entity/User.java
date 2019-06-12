package entity;

public class User {
	private static String username;
	private static String password;
	private static String stationName;
	private static int authority;
	public User() {
		
	}
	public User(String p1,String p2,String p3,int p4) {
		username=p1;
		password=p2;
		stationName=p3;
		authority=p4;
	}
}
