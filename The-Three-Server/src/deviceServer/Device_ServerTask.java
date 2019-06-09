package deviceServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.ServerTask;

public class Device_ServerTask extends ServerTask{

	public Device_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getCarLatestGPS(String carnum) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select GPS from Devicerecord where carNumber=? ORDER BY Time DESC");
			pstmt.setString(1,carnum);
			ResultSet res=pstmt.executeQuery();
			sb.append(res.getString(1));
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getCarGPSonRoute(int routeNumber) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select carNumber from Car where routeNumber=?");
			pstmt.setInt(1,routeNumber);
			ResultSet res=pstmt.executeQuery();
			while(res.next()) {
				String s=res.getString(1);
				sb.append(getCarLatestGPS(s));
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

}
