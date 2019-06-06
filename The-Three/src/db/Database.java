package db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import code.Code;
import code.IO;
import entity.Car;
import entity.Driver;
import entity.Route;

public class Database {
	private static String addr="127.0.0.1";//"cal.srcserver.xyz";
	public Database() {
		
	}
	
	public static Route getRouteInfo(int n){//查询路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "3");//查询车辆信息具体信息中的路线的信息
			IO.write(output, Integer.toString(n));//写入路线的编号
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			Route[] res=new Route[data.length/4];
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Route(Integer.valueOf(data[i+0]),data[i+1],data[i+2],data[i+3]);
			}
			return res[0];
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Driver getDriverInfo(int n){//查询驾驶员信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "2");//查询车辆信息具体信息中的驾驶员的信息
			IO.write(output, Integer.toString(n));//写入驾驶员编号
			String raw_string=IO.read(input);
			System.out.println(raw_string);
			String[] data=raw_string.split("#");
			Driver[] res=new Driver[data.length/7];
			for(int i=0;i<data.length;i+=7) {
				res[i/7]=new Driver(Integer.valueOf(data[i+0]),data[i+1],data[i+2],
						Integer.valueOf(data[i+3]),Integer.valueOf(data[i+4]),data[i+5],
						Integer.valueOf(data[i+6]));
			}
			return res[0];
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Car[] getCarInfo() {//查询车辆的概要信息
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "1");
			String raw_string=IO.read(input);
			System.out.println(raw_string);
			
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
	

	public static String[] getCarNumber(String s){//增加删除修改车辆信息时，获取车辆的车牌号
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "4");//查询车辆的编号
			IO.write(output, s);//查询的车牌号部分信息
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");//去掉#
			
			return data;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static String AddCarInfo(String newcarinfo){//对车辆信息的实际增加操作
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "5");//增加或者修改车辆信息
			IO.write(output, newcarinfo);//车辆的信息
			
			String raw_string=IO.read(input);
			
			return raw_string;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String ModifyCarInfo(String newcarinfo){
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "7");//修改车辆信息
			IO.write(output, newcarinfo);//车辆的信息
			
			String raw_string=IO.read(input);
			
			return raw_string;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String DeleteCarInfo(String newcarinfo){//对车辆信息的实际删除操作
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "6");//增加或者修改车辆信息
			IO.write(output, newcarinfo);//车辆的信息
			
			String raw_string=IO.read(input);
			
			return raw_string;
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
