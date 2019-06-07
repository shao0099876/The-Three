package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int port=8081;
	private static ServerSocket serverSocket;
	public static boolean DEBUG=true;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		serverSocket=new ServerSocket(port);
		while(true) {
			Socket socket=serverSocket.accept();
			DebugInfo.DebugInfo("接收到连接");
			Thread t=new Thread(new ServerTask(socket));
			t.start();
		}
	}
}
