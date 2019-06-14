package entity;

public class User {
	private static String username;
	private static String password;
	public static String stationName;
	private static int authority;
	public User() {
		
	}
	public User(String p1,String p2,String p3) {
		username=p1;
		password=p2;
		stationName=p3;
	}
}
