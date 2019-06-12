package station.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import code.IO;
import entity.Car;
import entity.Station;

public class Station_Database {
	private static String addr="cal.srcserver.xyz";

	public static String getGPS(String stationName) {
		// TODO Auto-generated method stub
		try {
			Socket socket=new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "26");
			IO.write(output, stationName);
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
			return raw_string;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static String AddStation(String stationName,String stationAddr) {
		try {
			Socket socket=new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "27");
			IO.write(output, stationName+"#"+stationAddr);
			String raw_string=IO.read(input);
			output.close();
			input.close();
			socket.close();
			return raw_string;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String delStation(String stationName) {
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "28");//���ӻ����޸ĳ�����Ϣ
			IO.write(output, stationName);//��������Ϣ
			
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
	public static String modifyStation(String stationName,String stationAddr) {
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "29");//�޸ĳ�����Ϣ
			IO.write(output, stationName+"#"+stationAddr);//��������Ϣ
			
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
	public static Station[] getStationList() {
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "30");//�޸ĳ�����Ϣ
			
			String raw_string=IO.read(input);
			String[] tmp=raw_string.split("#");
			Station[] res=new Station[tmp.length/3];
			for(int i=0;i<tmp.length;i+=3) {
				res[i/3]=new Station(tmp[i+0],tmp[i+1],tmp[i+2]);
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
	public static Station[] getMohuStationInfo(String s) {
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			IO.write(output, "31");//��ѯ�����ı��
			IO.write(output, s);//��ѯ�ĳ��ƺŲ�����Ϣ
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");//ȥ��#
			
			Station[] res=new Station[data.length/3];
			for(int i=0;i<data.length;i+=3) {
				res[i/3]=new Station(data[i],data[i+1],data[i+2]);
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
