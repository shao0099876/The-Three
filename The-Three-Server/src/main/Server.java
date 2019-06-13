package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static boolean DEBUG=true;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Thread t1=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket serverSocket = null;
				try {
					serverSocket = new ServerSocket(8081);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(true) {
					Socket socket = null;
					try {
						socket = serverSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DebugInfo.DebugInfo("接收到连接");
					Thread t=new Thread(new ServerTask(socket));
					t.start();
				}
			}
			
		});
		t1.start();
		Thread t2=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket serverSocket=null;
				try {
					serverSocket =new ServerSocket(8082);
				}catch(IOException e) {
					e.printStackTrace();
				}
				while(true) {
					Socket socket=null;
					try {
						socket=serverSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread t=new Thread(new DeviceServerTask(socket));
					t.start();
				}
			}
		});
		t2.start();
	}
}
