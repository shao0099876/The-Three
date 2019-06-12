package cargoServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ServerTask;

public class Cargo_ServerTask extends ServerTask{

	public Cargo_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getAllCargo() {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from Cargo");
			boolean flag=true;
			while(res.next()) {
				if(!flag) {
					sb.append("#");
				}
				flag=false;
				sb.append(res.getInt(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
				sb.append("#");
				sb.append(res.getInt(4));
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getSpecifiedCargo(int readInt) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from CargoRecord where cargoNumber=?");
			pstmt.setInt(1, readInt);
			ResultSet res=pstmt.executeQuery();
			boolean flag=true;
			while(res.next()) {
				if(!flag) {
					sb.append("#");
				}
				flag=false;
				sb.append(res.getInt(1));
				sb.append("#");
				sb.append(res.getInt(2));
				sb.append("#");
				sb.append(res.getString(3));
				sb.append("#");
				sb.append(res.getString(4));
				sb.append("#");
				sb.append(res.getString(5));
				sb.append("#");
				sb.append(res.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public static String addCargo(String s ) {
		// TODO Auto-generated method stub
		String[] data=s.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int cargoNumber = 0;
		try {
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select COUNT(*) from Cargo");
			cargoNumber=res.getInt(1)+1;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据类型转换
		
		String start=data[0];
		String end=data[1];
		int routeNumber=Integer.valueOf(data[2]);
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into Cargo values(?,?,?,?)");
			pstmt.setInt(1, cargoNumber);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			pstmt.setInt(4, routeNumber);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(cargoNumber);
	}

	public static void addCargoRecord(String s) {
		// TODO Auto-generated method stub
		String[] data=s.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int recordNumber = 0;
		try {
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select COUNT(*) from CargoRecord");
			recordNumber=res.getInt(1)+1;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据类型转换
		int cargoNumber=Integer.valueOf(data[0]);
		String carNumber=data[1];
		
		SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=ft.format(new Date());
		
		String stationName=data[2];
		int type=Integer.valueOf(data[3]);
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into CargoRecord values(?,?,?,?,?,?)");
			pstmt.setInt(1, recordNumber);
			pstmt.setInt(2, cargoNumber);
			pstmt.setString(3, carNumber);
			pstmt.setString(4, time);
			pstmt.setString(5, stationName);
			pstmt.setInt(6, type);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
