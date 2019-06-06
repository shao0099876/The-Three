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
	
	public static Route getRouteInfo(int n){//��ѯ·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "3");//��ѯ������Ϣ������Ϣ�е�·�ߵ���Ϣ
			IO.write(output, Integer.toString(n));//д��·�ߵı��
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
	
	public static Driver getDriverInfo(int n){//��ѯ��ʻԱ��Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "2");//��ѯ������Ϣ������Ϣ�еļ�ʻԱ����Ϣ
			IO.write(output, Integer.toString(n));//д���ʻԱ���
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
	
	public static Car[] getCarInfo() {//��ѯ�����ĸ�Ҫ��Ϣ
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
	

	public static String[] getCarNumber(String s){//����ɾ���޸ĳ�����Ϣʱ����ȡ�����ĳ��ƺ�
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "4");//��ѯ�����ı��
			IO.write(output, s);//��ѯ�ĳ��ƺŲ�����Ϣ
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");//ȥ��#
			
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


	public static String AddCarInfo(String newcarinfo){//�Գ�����Ϣ��ʵ�����Ӳ���
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "5");//���ӻ����޸ĳ�����Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
			IO.write(output, "7");//�޸ĳ�����Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
	
	public static String DeleteCarInfo(String newcarinfo){//�Գ�����Ϣ��ʵ��ɾ������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "6");//���ӻ����޸ĳ�����Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
