package car.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Car;

public class Car_Database {
	private static String addr="cal.srcserver.xyz";//"127.0.0.1";//"cal.srcserver.xyz";//"cal.srcserver.xyz"
	public static Car[] getCarInfo() {//��ѯ�����ĸ�Ҫ��Ϣ
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
	public static String[] getCarNumber(String s){//����ɾ���޸ĳ�����Ϣʱ����ȡ�����ĳ��ƺ�
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "4");//��ѯ�����ı��
			IO.write(output, s);//��ѯ�ĳ��ƺŲ�����Ϣ
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");//ȥ��#
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
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "5");//���ӻ����޸ĳ�����Ϣ
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
	public static String ModifyCarInfo(String newcarinfo){
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
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "6");//���ӻ����޸ĳ�����Ϣ
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
}
