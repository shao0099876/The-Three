package main;

public class DebugInfo {
	public static void DebugInfo(String s) {
		if(Server.DEBUG) {
			System.out.println(s);
		}
	}
}
