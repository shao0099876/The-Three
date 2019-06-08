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
	private Socket socket;
	public ServerTask(Socket p) {
		socket=p;
	}
	private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static String DB_URL = "jdbc:sqlserver://localhost:1433;user=SA;password=SHAO0123ruo;";
	private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	//private static String DB_URL = "jdbc:sqlserver://cal.srcserver.xyz:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	protected static Connection conn=null;
	public static void initDB() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);//注册驱动
		conn=DriverManager.getConnection(DB_URL);//打开链接
		//执行查询
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			int op=IO.readInt(input);//读入操作数
			System.out.print(op+"\n");
			switch(op) {
			case 1:
				IO.write(output, Car_ServerTask.getCarInfo());
				break;
			case 2:
				System.out.println("readDriverNum_before");
				int peonum=IO.readInt(input);//读入驾驶员编号
				System.out.println(peonum);
				IO.write(output, Driver_ServerTask.getDriverInfo(peonum));
				break;
			case 3:
				int routnum=IO.readInt(input);//读入路线的编号
				IO.write(output, Route_ServerTask.getRouteInfo(routnum));
				break;
			case 4:
				String s=IO.read(input);//读进来部分车牌号信息
				System.out.println(s);
				IO.write(output, Car_ServerTask.getCarNum(s));
				break;
			case 5:
				String carinfo=IO.read(input);//将要进行修改的车辆信息读进来
				System.out.println(carinfo);//测试
				IO.write(output, Car_ServerTask.AddCarInfo(carinfo));
				break;
			case 6:
				String delcarinfo=IO.read(input);//将要进行删除的车辆信息读进来
				System.out.println(delcarinfo);//测试
				IO.write(output, Car_ServerTask.DelCarInfo(delcarinfo));
				break;
			case 7:
				String modcarinfo=IO.read(input);//将要进行删除的车辆信息读进来
				System.out.println(modcarinfo);//测试
				IO.write(output, Car_ServerTask.ModCarInfo(modcarinfo));
				break;
			case 8:
				IO.write(output, Route_ServerTask.getAllRouteInfo());//查询所有路线信息
				break;
			case 9:
				String delrouteinfo=IO.read(input);//将要进行模糊查询的路线信息读进来
				System.out.println(delrouteinfo);//测试
				IO.write(output, Route_ServerTask.getMoHuRouteInfo(delrouteinfo));
				break;
			case 10:
				String del_routeinfo=IO.read(input);//将要进行删除的路线信息读进来
				System.out.println(del_routeinfo);//测试
				IO.write(output, Route_ServerTask.delRouteInfo(del_routeinfo));
				break;
			case 11:
				System.out.println("开始接受添加路线信息");
				String add_routeinfo=IO.read(input);//将要添加的路线信息读进来
				System.out.println(add_routeinfo);//测试
				IO.write(output, Route_ServerTask.addOneRouteInfo(add_routeinfo));
				break;
			case 12:
				String mod_routeinfo=IO.read(input);//将要添加的路线信息读进来
				System.out.println(mod_routeinfo);//测试
				IO.write(output, Route_ServerTask.modOneRouteInfo(mod_routeinfo));
				break;
			
			}
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//关闭连接
	}
	
	
	
	
	
	
	

	


	

	
	
	
}
