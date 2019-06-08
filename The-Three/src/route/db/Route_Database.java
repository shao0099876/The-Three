package route.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Route;

public class Route_Database {
	private static String addr="cal.srcserver.xyz";//"127.0.0.1";//"cal.srcserver.xyz";//"cal.srcserver.xyz";
	public static Route getRouteInfo(int n){//查询单条路线信息
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

	public static Route[] getAllRouteInfo(){//查询所有路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "8");//查询所有路线的信息
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			Route[] res=new Route[data.length/4];
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Route(Integer.valueOf(data[i+0]),data[i+1],data[i+2],data[i+3]);
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

	public static Route[] getMohuRouteInfo(String s){//模糊查询路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "9");//模糊查询路线信息
			System.out.println("开始发送删除需要用的模糊查询路线编号");
			IO.write(output, s);//模糊查询路线的路线信息片段
			System.out.println("发送删除需要用的模糊查询路线编号结束");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			Route[] res=new Route[data.length/4];
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Route(Integer.valueOf(data[i+0]),data[i+1],data[i+2],data[i+3]);
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

	public static String[] getMohuRouteNumInfo(String s){//模糊查询路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "9");//模糊查询路线信息
			System.out.println("开始发送模糊查询文本框里面的路线编号");
			IO.write(output, s);//模糊查询路线的路线信息片段
			System.out.println("发送模糊查询文本框里面的路线编号结束");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			
			String[] res=new String[data.length];//用来保存编号
			
			for(int i=0;i<data.length;i+=4) {
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

	public static String delRouteInfo(String s){//删除具体的路线信息
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "10");//删除具体的路线信息
			IO.write(output, s);//路线编号
			
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

	public static String addRouteInfo(String[] s){//增加路线信息
		try {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<s.length;i++){
				if(i==(s.length-1)){
					sb.append(s[i]);
				}
				else{
					sb.append(s[i]);
					sb.append("#");
				}
			}
			
			String S=sb.toString();//将路线信息转化为字符创
			System.out.println("增加路线信息为"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "11");//增加路线信息
			System.out.println("开始发送添加路线信息");
			IO.write(output, S);//路线的信息
			System.out.println("发送添加路线信息结束");
			
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

	public static String modRouteInfo(String[] s){//修改具体的路线信息
		try {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<s.length;i++){
				if(i==(s.length-1)){
					sb.append(s[i]);
				}
				sb.append(s[i]);
				sb.append("#");
			}
			
			String S=sb.toString();//将路线信息转化为字符创
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "12");//增加或者修改车辆信息
			IO.write(output, S);//车辆的信息
			
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
