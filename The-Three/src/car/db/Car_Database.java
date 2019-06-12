package car.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.DebugInfo;
import code.IO;
import entity.Car;
import entity.GPS;

public class Car_Database {
	//private static String addr="cal.srcserver.xyz";
	private static String addr="47.105.101.104";
	public static Car[] getCarInfo() {//��ѯ�����ĸ�Ҫ��Ϣ
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "1");
			
			String raw_string=IO.read(input);
			
			String[] data=raw_string.split("#");
			Car[] res=new Car[data.length/4];
			
			for(int i=0;i<data.length;i+=4) {
				res[i/4]=new Car(data[i+0],Integer.valueOf(data[i+1]),Integer.valueOf(data[i+2]),Integer.valueOf(data[i+3]));
			}
			output.close();
			input.close();
			socket.close();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String[] getCarNumber(String s){//����ɾ���޸ĳ�����Ϣʱ����ȡ�����ĳ��ƺ�
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "4");//��ѯ�����ı��
			IO.write(output, s);//��ѯ�ĳ��ƺŲ�����Ϣ
			
			String raw_string=IO.read(input);
			
			String[] data1=raw_string.split("#");//ȥ��#
			String data[]=new String[data1.length/4];
			if(raw_string.length()!=0) {
				for(int i=0;i<data1.length;i+=4){
					data[i/4]=data1[i];
				}
			}
			output.close();
			input.close();
			socket.close();
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
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "5");//���ӳ�����Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
	public static String ModifyCarInfo(String newcarinfo){//�޸ĳ�����Ϣ
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "7");//�޸ĳ�����Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
	public static String DeleteCarInfo(String newcarinfo){//�Գ�����Ϣ��ʵ��ɾ������
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "6");//ɾ��������Ϣ
			IO.write(output, newcarinfo);//��������Ϣ
			
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
	public static String getCarGPS() {
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "14");//��ѯ���г���GPSλ����Ϣ
			
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
	public static String getCarNumberonRoute(String routeNumber) {
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "15");//��ȡ�ڸ�·���ϵ����г���
			IO.write(output, routeNumber);
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
	public static String getAllCarNumber() {
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "16");//��ѯ���г������ƺ�
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
	public static String getSpecifiedCarGPS(String carNum) {
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "17");//��ѯ�ض�����GPSλ����Ϣ
			IO.write(output, carNum);
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

	public static Car[] getMohuCarInfo(String s){//ģ����ѯ������������Ϣ
		//�������
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "4");//��ѯ�����ı��
			IO.write(output, s);//��ѯ�ĳ��ƺŲ�����Ϣ
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");//ȥ��#
			
			Car[] res=new Car[data.length/4];
			for(int i=0;i<data.length;i+=4){
				res[i/4]=new Car(data[i],Integer.valueOf(data[i+1]),Integer.valueOf(data[i+2]),Integer.valueOf(data[i+3]));
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
}
