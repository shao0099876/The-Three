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
import cargoServer.Cargo_ServerTask;
import deviceServer.Device_ServerTask;
import driverServer.Driver_ServerTask;
import routeServer.Route_ServerTask;
import stationServer.Station_ServerTask;
import userServer.User_ServerTask;
import iotools.IO;

public class ServerTask implements Runnable {
	protected Socket socket;
	public ServerTask(Socket p) {
		socket=p;
	}
	private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static String DB_URL = "jdbc:sqlserver://localhost:1433;user=SA;password=SHAO0123ruo;";
	private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	//private static String DB_URL = "jdbc:sqlserver://cal.srcserver.xyz:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	protected static Connection conn=null;
	public static void initDB() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);//ע������
		conn=DriverManager.getConnection(DB_URL);//������
		//ִ�в�ѯ
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			int op=IO.readInt(input);//���������
			System.out.print(op+"\n");
			switch(op) {
			case 1:
				IO.write(output, Car_ServerTask.getCarInfo());//��ѯ������Ҫ��Ϣ
				break;
			case 2:
				System.out.println("readDriverNum_before");//��ѯ�����ʻԱ��Ϣ
				int peonum=IO.readInt(input);//�����ʻԱ���
				System.out.println(peonum);
				IO.write(output, Driver_ServerTask.getDriverInfo(peonum));
				break;
			case 3:
				IO.write(output, Route_ServerTask.getRouteInfo(IO.readInt(input)));
				break;
			case 4:
				IO.write(output, Car_ServerTask.getMohuCarInfo(IO.read(input)));
				break;
			case 5:
				IO.write(output, Car_ServerTask.AddCarInfo(IO.read(input)));
				break;
			case 6:
				IO.write(output, Car_ServerTask.DelCarInfo(IO.read(input)));
				break;
			case 7:
				IO.write(output, Car_ServerTask.ModCarInfo(IO.read(input)));
				break;
			case 8:
				String tmp=Route_ServerTask.getAllRouteInfo();
				System.out.println(tmp);
				IO.write(output, tmp);//��ѯ����·����Ϣ
				break;
			case 9:
				String delrouteinfo=IO.read(input);//��Ҫ����ģ����ѯ��·����Ϣ������
				System.out.println(delrouteinfo);//����
				IO.write(output, Route_ServerTask.getMoHuRouteInfo(delrouteinfo));
				break;
			case 10:
				String del_routeinfo=IO.read(input);//��Ҫ����ɾ����·����Ϣ������
				System.out.println(del_routeinfo);//����
				IO.write(output, Route_ServerTask.delRouteInfo(del_routeinfo));
				break;
			case 11:
				System.out.println("��ʼ�������·����Ϣ");
				String add_routeinfo=IO.read(input);//��Ҫ��ӵ�·����Ϣ������
				System.out.println(add_routeinfo);//����
				IO.write(output, Route_ServerTask.addOneRouteInfo(add_routeinfo));
				break;
			case 12:
				String mod_routeinfo=IO.read(input);//��Ҫ�޸ĵ�·����Ϣ������
				System.out.println(mod_routeinfo);//����
				IO.write(output, Route_ServerTask.modOneRouteInfo(mod_routeinfo));
				break;
			case 13:
				IO.write(output, Device_ServerTask.getCarGPSonRoute(IO.readInt(input)));
				break;
			case 14:
				IO.write(output, Device_ServerTask.getCarGPS());
				break;
			case 15:
				IO.write(output, Car_ServerTask.getCarNumberonRoute(IO.readInt(input)));
				break;
			case 16:
				IO.write(output, Car_ServerTask.getAllCarNumber());
				break;
			case 17:
				IO.write(output, Device_ServerTask.getSpecifiedCarGPS(IO.read(input)));
				break;
			case 18:
				IO.write(output, Cargo_ServerTask.getAllCargo());
				break;
			case 19:
				IO.write(output, Cargo_ServerTask.getSpecifiedCargo(IO.readInt(input)));
				break;
			case 20:
				System.out.println("��ʼ��ѯ���м�ʻԱ��Ϣ");
				IO.write(output, Driver_ServerTask.getAllDriverInfo());//��ѯ���м�ʻԱ��Ϣ
				break;
			case 21:
				String adddriverinfo=IO.read(input);//����ӵļ�ʻԱ��Ϣ������
				System.out.println(adddriverinfo);//����
				IO.write(output, Driver_ServerTask.addDriverInfo(adddriverinfo));//��ѯ���м�ʻԱ��Ϣ
				break;
			case 22:
				String moddriverinfo=IO.read(input);//���޸ĵļ�ʻԱ��Ϣ������
				System.out.println(moddriverinfo);//����
				IO.write(output, Driver_ServerTask.modDriverInfo(moddriverinfo));//��ѯ���м�ʻԱ��Ϣ
				break;
			case 23:
				String mohuinfo=IO.read(input);//��ģ����ѯ��ʻԱ����Ϣ������
				System.out.println(mohuinfo);//����
				IO.write(output, Driver_ServerTask.mohuDriverInfo(mohuinfo));//ģ����ѯ��ʻԱ��Ϣ
				break;
			case 24:
				String delnum=IO.read(input);//��ɾ���ļ�ʻԱ��Ŷ�����
				System.out.println(delnum);//����
				IO.write(output, Driver_ServerTask.delDriverInfo(delnum));//ɾ����ʻԱ
				break;
			case 25:
				IO.write(output, Cargo_ServerTask.addCargo(IO.read(input)));
				break;
			case 26:
				String stationName=IO.read(input);
				DebugInfo.DebugInfo(stationName);
				IO.write(output, Station_ServerTask.getGPS(stationName));
				break;
			case 27:
				String stationInfo=IO.read(input);
				IO.write(output, Station_ServerTask.addStation(stationInfo));
				break;
			case 28:
				IO.write(output, Station_ServerTask.delStation(IO.read(input)));
				break;
			case 29:
				IO.write(output, Station_ServerTask.modifyStation(IO.read(input)));
				break;
			case 30:
				IO.write(output, Station_ServerTask.getStationList());
				break;
			case 31:
				IO.write(output, Station_ServerTask.getMohuStationList(IO.read(input)));
				break;
			case 32:
				IO.write(output, Route_ServerTask.getMRouteInfo(IO.read(input)));
				break;
			case 33:
				IO.write(output, User_ServerTask.login(IO.read(input)));
				break;
			case 34:
				IO.write(output, User_ServerTask.getUser(IO.read(input)));
				break;
			case 35:
				Cargo_ServerTask.addCargoRecord(IO.read(input));
				break;
			case 36:
				Station_ServerTask.addStationRecord(IO.read(input));
				break;
			case 37:
				IO.write(output, Car_ServerTask.getLatestDeviceRecord(IO.read(input)));
				break;
			case 38:
				IO.write(output, Cargo_ServerTask.getCargoStatus(IO.read(input)));
				break;
			}
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�ر�����
	}
	
	
	
	
	
	
	

	


	

	
	
	
}
