package db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Driver;
import java.util.ArrayList;

import entity.Car;

public class Database {
	private static String addr="192.168.43.21";//"cal.srcserver.xyz";
	public Database() {
		
	}
	
	public static Car[] getCarInfo() {//查询车辆的概要信息
		// TODO Auto-generated method stub
		try {
			//System.out.println("1");
			Socket socket= new Socket(addr,8081);
			//System.out.println("2");
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			output.writeInt(1);
			output.flush();
			
			String raw_string=input.readLine();
			String[] data=raw_string.split("#");
			for(int i=0;i<data.length;i++) {
				data[i]=data[i].substring(1, data[i].length()-1);
			}
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
	
	public static Driver[] getDriverInfo(){
		return null;
		
	}
	
}
