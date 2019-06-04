package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		newThread();
	}
	public static void newThread() {
		Thread t=new Thread(new ServerTask());
		t.start();
	}
}
