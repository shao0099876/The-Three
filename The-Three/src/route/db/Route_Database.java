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
	public static Route getRouteInfo(int n){//��ѯ����·����Ϣ
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

	public static Route[] getAllRouteInfo(){//��ѯ����·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "8");//��ѯ����·�ߵ���Ϣ
			
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

	public static Route[] getMohuRouteInfo(String s){//ģ����ѯ·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "9");//ģ����ѯ·����Ϣ
			System.out.println("��ʼ����ɾ����Ҫ�õ�ģ����ѯ·�߱��");
			IO.write(output, s);//ģ����ѯ·�ߵ�·����ϢƬ��
			System.out.println("����ɾ����Ҫ�õ�ģ����ѯ·�߱�Ž���");
			
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

	public static String[] getMohuRouteNumInfo(String s){//ģ����ѯ·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "9");//ģ����ѯ·����Ϣ
			System.out.println("��ʼ����ģ����ѯ�ı��������·�߱��");
			IO.write(output, s);//ģ����ѯ·�ߵ�·����ϢƬ��
			System.out.println("����ģ����ѯ�ı��������·�߱�Ž���");
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			
			String[] res=new String[data.length];//����������
			
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

	public static String delRouteInfo(String s){//ɾ�������·����Ϣ
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "10");//ɾ�������·����Ϣ
			IO.write(output, s);//·�߱��
			
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

	public static String addRouteInfo(String[] s){//����·����Ϣ
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
			
			String S=sb.toString();//��·����Ϣת��Ϊ�ַ���
			System.out.println("����·����ϢΪ"+S);
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "11");//����·����Ϣ
			System.out.println("��ʼ�������·����Ϣ");
			IO.write(output, S);//·�ߵ���Ϣ
			System.out.println("�������·����Ϣ����");
			
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

	public static String modRouteInfo(String[] s){//�޸ľ����·����Ϣ
		try {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<s.length;i++){
				if(i==(s.length-1)){
					sb.append(s[i]);
				}
				sb.append(s[i]);
				sb.append("#");
			}
			
			String S=sb.toString();//��·����Ϣת��Ϊ�ַ���
			
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "12");//���ӻ����޸ĳ�����Ϣ
			IO.write(output, S);//��������Ϣ
			
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
