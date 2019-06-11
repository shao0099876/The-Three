package driver.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Driver;

public class Driver_Database {
	private static String addr="cal.srcserver.xyz";//"cal.srcserver.xyz";
	public static Driver getDriverInfo(int n){//查询某一具体驾驶员信息
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
			
			output.close();
			input.close();
			socket.close();
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
	
	public static Driver[] getAllDriverInfo(){//查询所有驾驶员信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "20");//查询所有驾驶员信息		
			
			String raw_string=IO.read(input);
			System.out.println(raw_string);
			String[] data=raw_string.split("#");
			Driver[] res=new Driver[data.length/7];
			
			for(int i=0;i<data.length;i+=7) {
				res[i/7]=new Driver(Integer.valueOf(data[i+0]),data[i+1],data[i+2],
						Integer.valueOf(data[i+3]),Integer.valueOf(data[i+4]),data[i+5],
						Integer.valueOf(data[i+6]));
			}
			
			output.close();
			input.close();
			socket.close();
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
	
	public static String addDriverInfo(String s){//增加驾驶员信息
		try {
			String S=s;
			System.out.println("增加驾驶员信息为"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output,"21");//增加驾驶员信息
			System.out.println("开始发送添加驾驶员信息");
			IO.write(output,S);//驾驶员信息
			System.out.println("发送添加驾驶员信息结束");
			
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
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

	public static String modifyDriverInfo(String s){//修改驾驶员信息
		try {
			String S=s;
			System.out.println("修改驾驶员信息为"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output,"22");//修改驾驶员信息
			System.out.println("开始发送修改驾驶员信息");
			IO.write(output,S);//驾驶员信息
			System.out.println("发送修改驾驶员信息结束");
			
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
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

	public static String[] getMohuDriverNumInfo(String s){//模糊查询驾驶员的编号
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "23");//模糊查询驾驶员编号
			System.out.println("开始发送删除需要用的模糊查询驾驶员编号");
			IO.write(output, s);//模糊查询路线的路线信息片段
			System.out.println("发送删除需要用的模糊查询驾驶员编号结束");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			
			String[] res=new String[data.length];//用来保存编号
			
			for(int i=0;i<data.length;i+=7) {
				res[i]=data[i];
			}
			output.close();
			input.close();
			socket.close();
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

	public static Driver[] getMohuDriverInfo(String s){//模糊查询驾驶员信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "23");//模糊查询驾驶员编号
			System.out.println("开始发送删除需要用的模糊查询驾驶员编号");
			IO.write(output, s);//模糊查询路线的路线信息片段
			System.out.println("发送删除需要用的模糊查询驾驶员编号结束");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			
			Driver[] res=new Driver[data.length/7];
			
			for(int i=0;i<data.length;i+=7) {
				res[i]=new Driver(Integer.valueOf(data[0]),data[1],data[2],Integer.valueOf(data[3]),Integer.valueOf(data[4]),data[5],Integer.valueOf(data[6]));
			}
			
			output.close();
			input.close();
			socket.close();
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

	public static String delDriverInfo(String s){//删除驾驶员信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "24");//删除具体的驾驶员
			IO.write(output, s);//驾驶员编号
			
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
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
