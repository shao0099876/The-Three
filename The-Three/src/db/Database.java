package db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import entity.Car;
import entity.Driver;
import entity.Route;

public class Database {
	private static String addr="192.168.43.21";//"cal.srcserver.xyz";
	public Database() {
		
	}
	
	public static Route getRouteInfo(int n){//��ѯ·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			output.writeInt(3);//��ѯ������Ϣ������Ϣ�е�·�ߵ���Ϣ
			output.writeInt(n);//д��·�ߵı��
			output.flush();
			String raw_string=input.readLine();
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
	
	public static Driver getDriverInfo(int n){//��ѯ��ʻԱ��Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			output.writeInt(2);//��ѯ������Ϣ������Ϣ�еļ�ʻԱ����Ϣ
			output.writeInt(n);//д���ʻԱ���
			output.flush();
			String raw_string=input.readLine();
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
	
	public static Car[] getCarInfo() {//��ѯ�����ĸ�Ҫ��Ϣ
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8081);
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
