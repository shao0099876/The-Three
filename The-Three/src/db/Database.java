package db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import entity.Car;

public class Database {
	private static String addr="127.0.0.1";//"cal.srcserver.xyz";
	public Database() {
		
	}

	public static Car[] getCarInfo() {
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8080);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			output.writeChars("1");
			output.flush();
			String raw_string=input.readLine();
			String[] data=raw_string.split("#");
			Car[] res=new Car[data.length/4];
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Car(data[i+0],Integer.valueOf(data[i+1]),Integer.valueOf(data[i+2]),Integer.valueOf(data[i+3]));
			}
			return res;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
