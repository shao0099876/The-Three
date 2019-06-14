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
			ResultSet res=stmt.executeQuery("select cargoNumber from Cargo");
			while(res.next()) {
				cargoNumber+=1;
			}
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
		System.out.println(s);
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
			ResultSet res=stmt.executeQuery("select recordNumber from CargoRecord");
			while(res.next()) {
				recordNumber+=1;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据类型转换
		int cargoNumber=Integer.valueOf(data[0]);
		String carNumber=data[2];
		
		SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=ft.format(new Date());
		
		String stationName=data[1];
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

	public static String getCargoStatus(String read) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement stmt=conn.prepareStatement("select TOP 1 * from CargoRecord where cargoNumber=? order by time desc");
			stmt.setString(1, read);
			ResultSet res=stmt.executeQuery();
			String stationName = null;int type = 0;
			while(res.next()) {
				stationName=res.getString(5);
				type=res.getInt(6);
			}
			res.close();
			stmt.close();
			stmt=conn.prepareStatement("select * from Cargo where cargoNumber=?");
			stmt.setString(1, read);
			res=stmt.executeQuery();
			String startAddr = null,endAddr = null;
			while(res.next()) {
				startAddr=res.getString(2);
				endAddr=res.getString(3);
			}
			res.close();
			stmt.close();
			conn.close();
			StringBuilder sb=new StringBuilder();
			sb.append(startAddr);
			sb.append("#");
			sb.append(endAddr);
			sb.append("#");
			switch(type) {
			case 1:
				sb.append(stationName);
				sb.append("已收件");
				break;
			case 2:
				sb.append(stationName);
				sb.append("已发车");
				break;
			case 3:
				sb.append(stationName);
				sb.append("已到达");
				break;
			case 4:
				sb.append(stationName);
				sb.append("已派件");
			}
			return sb.toString();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
