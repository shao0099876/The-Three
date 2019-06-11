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
	public static Driver getDriverInfo(int n){//��ѯĳһ�����ʻԱ��Ϣ
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
	
	public static Driver[] getAllDriverInfo(){//��ѯ���м�ʻԱ��Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "20");//��ѯ���м�ʻԱ��Ϣ		
			
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
	
	public static String addDriverInfo(String s){//���Ӽ�ʻԱ��Ϣ
		try {
			String S=s;
			System.out.println("���Ӽ�ʻԱ��ϢΪ"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output,"21");//���Ӽ�ʻԱ��Ϣ
			System.out.println("��ʼ������Ӽ�ʻԱ��Ϣ");
			IO.write(output,S);//��ʻԱ��Ϣ
			System.out.println("������Ӽ�ʻԱ��Ϣ����");
			
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

	public static String modifyDriverInfo(String s){//�޸ļ�ʻԱ��Ϣ
		try {
			String S=s;
			System.out.println("�޸ļ�ʻԱ��ϢΪ"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output,"22");//�޸ļ�ʻԱ��Ϣ
			System.out.println("��ʼ�����޸ļ�ʻԱ��Ϣ");
			IO.write(output,S);//��ʻԱ��Ϣ
			System.out.println("�����޸ļ�ʻԱ��Ϣ����");
			
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

	public static String[] getMohuDriverNumInfo(String s){//ģ����ѯ��ʻԱ�ı��
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "23");//ģ����ѯ��ʻԱ���
			System.out.println("��ʼ����ɾ����Ҫ�õ�ģ����ѯ��ʻԱ���");
			IO.write(output, s);//ģ����ѯ·�ߵ�·����ϢƬ��
			System.out.println("����ɾ����Ҫ�õ�ģ����ѯ��ʻԱ��Ž���");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			
			String[] res=new String[data.length];//����������
			
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

	public static Driver[] getMohuDriverInfo(String s){//ģ����ѯ��ʻԱ��Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "23");//ģ����ѯ��ʻԱ���
			System.out.println("��ʼ����ɾ����Ҫ�õ�ģ����ѯ��ʻԱ���");
			IO.write(output, s);//ģ����ѯ·�ߵ�·����ϢƬ��
			System.out.println("����ɾ����Ҫ�õ�ģ����ѯ��ʻԱ��Ž���");
			
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

	public static String delDriverInfo(String s){//ɾ����ʻԱ��Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "24");//ɾ������ļ�ʻԱ
			IO.write(output, s);//��ʻԱ���
			
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
