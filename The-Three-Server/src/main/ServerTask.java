package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import carServer.Car_ServerTask;
import driverServer.Driver_ServerTask;
import routeServer.Route_ServerTask;
import iotools.IO;

public class ServerTask implements Runnable {

	private static int port=8081;
	private ServerSocket serverSocket;
	private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static String DB_URL = "jdbc:sqlserver://localhost:1433;user=SA;password=SHAO0123ruo;";
	private static String DB_URL = "jdbc:sqlserver://cal.srcserver.xyz:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	protected static Connection conn=null;
	public static void initDB() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);//ע������
		conn=DriverManager.getConnection(DB_URL);//������
		//ִ�в�ѯ
	}
//	private ResultSet query(String sql) throws SQLException {
//		ResultSet rs=stmt.executeQuery(sql);
//		return rs;
//	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			try {
				Socket socket=serverSocket.accept();
				DataInputStream input=new DataInputStream(socket.getInputStream());
				DataOutputStream output=new DataOutputStream(socket.getOutputStream());
				int op=IO.readInt(input);//���������
				System.out.print(op+"\n");
				switch(op) {
				case 1:
					IO.write(output, Car_ServerTask.getCarInfo());
					break;
				case 2:
					System.out.println("readDriverNum_before");
					int peonum=IO.readInt(input);//�����ʻԱ���
					System.out.println(peonum);
					IO.write(output, Driver_ServerTask.getDriverInfo(peonum));
					break;
				case 3:
					int routnum=IO.readInt(input);//����·�ߵı��
					IO.write(output, Route_ServerTask.getRouteInfo(routnum));
					break;
				case 4:
					String s=IO.read(input);//���������ֳ��ƺ���Ϣ
					System.out.println(s);
					IO.write(output, Car_ServerTask.getCarNum(s));
					break;
				case 5:
					String carinfo=IO.read(input);//��Ҫ�����޸ĵĳ�����Ϣ������
					System.out.println(carinfo);//����
					IO.write(output, Car_ServerTask.AddCarInfo(carinfo));
					break;
				case 6:
					String delcarinfo=IO.read(input);//��Ҫ����ɾ���ĳ�����Ϣ������
					System.out.println(delcarinfo);//����
					IO.write(output, Car_ServerTask.DelCarInfo(delcarinfo));
					break;
				case 7:
					String modcarinfo=IO.read(input);//��Ҫ����ɾ���ĳ�����Ϣ������
					System.out.println(modcarinfo);//����
					IO.write(output, Car_ServerTask.ModCarInfo(modcarinfo));
					break;
				}
				input.close();
				output.close();
				socket.close();//�ر�����
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	

	


	

	
	
	
}
