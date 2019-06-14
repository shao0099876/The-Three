package userServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ServerTask;

public class User_ServerTask extends ServerTask{

	public User_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String login(String read) {
		// TODO Auto-generated method stub
		System.out.println(read);
		String[] data=read.split("#");
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from UserList where username=? and password=?");
			pstmt.setString(1,data[0]);
			pstmt.setString(2, data[1]);
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				sb.append("true");
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

	public static String getUser(String read) {
		// TODO Auto-generated method stub
		String username=read;
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from UserList where username=?");
			pstmt.setString(1,username);
			ResultSet res=pstmt.executeQuery();
			while(res.next()){
				sb.append(res.getString(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
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
