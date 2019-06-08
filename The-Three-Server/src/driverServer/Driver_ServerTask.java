package driverServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ServerTask;

public class Driver_ServerTask extends ServerTask {
	public Driver_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getDriverInfo(int peonum) {//查询驾驶员信息
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			System.out.println("DriverQuery_before");
			PreparedStatement pstmt=conn.prepareStatement("select * from Driver where peopleNumber=?");
			pstmt.setInt(1,peonum);
			ResultSet res=pstmt.executeQuery();
			System.out.println("DriverQuery_after");
			boolean flag=true;
			while(res.next()){
				if(!flag){
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
				sb.append("#");
				sb.append(res.getInt(5));
				sb.append("#");
				sb.append(res.getString(6));
				sb.append("#");
				sb.append(res.getInt(7));
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
