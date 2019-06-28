package carServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.DebugInfo;
import main.ServerTask;

public class Car_ServerTask extends ServerTask{
	public Car_ServerTask(Socket p) {
		super(p);
	}
	public static String getCarInfo() {//查车队概要列表
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select * from Car");
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getString(1));sb.append("#");
				sb.append(res.getInt(2));sb.append("#");
				sb.append(res.getInt(3));sb.append("#");
				sb.append(res.getInt(4));
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getMohuCarInfo(String s){//查询车辆的信息（模糊查询）
		//测试完成
		s=s+"%";//实现模糊查询
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Car where carNumber like ?");
			pstmt.setString(1,s);
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getString(1));
				sb.append("#");
				sb.append(res.getInt(2));
				sb.append("#");
				sb.append(res.getInt(3));
				sb.append("#");
				sb.append(res.getInt(4));
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
	
	public static String AddCarInfo(String s){//增加车辆信息
		//测试完成
		String message="";//操作结果
		String[] data=s.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		String newcarNumber=data[0];
		int newpeople1Number=Integer.valueOf(data[1]);
		int newpeople2Number=Integer.valueOf(data[2]);
		int newrouteNumber=Integer.valueOf(data[3]);
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber = ?");
			pstmt.setString(1,newcarNumber);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，为添加
				PreparedStatement pstmt1=conn.prepareStatement("insert into Car values(?,?,?,?)");
				pstmt1.setString(1,newcarNumber);
				pstmt1.setInt(2,newpeople1Number);
				pstmt1.setInt(3,newpeople2Number);
				pstmt1.setInt(4,newrouteNumber);	
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="添加信息成功";
			}
			if(n>0){//非空，不能添加
				message="存在车辆信息，不能添加";
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public static String DelCarInfo(String s){//用于删除车辆信息
		//测试完成
		String[] data=s.split("#");//获取信息
		String message="";
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber = ?");
			pstmt.setString(1,data[0]);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，没有办法进行删除操作
				message="没有该车辆信息，没有办法进行删除";
			}
			if(n>0){//非空，进行删除
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Car where carNumber=?");
					pstmt0.setString(1,data[0]);
					pstmt0.executeUpdate();  
					pstmt0.close();
					message="删除成功";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public static String ModCarInfo(String s){//用于修改车辆信息
		//测试完成
		String message="";//操作结果
		
		String[] data=s.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		String newcarNumber=data[0];
		int newpeople1Number=Integer.valueOf(data[1]);
		int newpeople2Number=Integer.valueOf(data[2]);
		int newrouteNumber=Integer.valueOf(data[3]);
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber = ?");
			pstmt.setString(1,newcarNumber);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，无法修改
				message="不存在该车辆信息，不能修改";
			}
			if(n>0){//非空，有结果，可以进行修改
				//待修改
				PreparedStatement pstmt2=conn.prepareStatement("update Car set people1Number = ? , people2Number = ? , routeNumber = ? where carNumber = ?");			
				pstmt2.setInt(1,newpeople1Number);
				pstmt2.setInt(2,newpeople2Number);
				pstmt2.setInt(3,newrouteNumber);	
				pstmt2.setString(4,newcarNumber);
				pstmt2.executeUpdate();
				pstmt2.close();
				message="修改成功";
			}
			res.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public static String getCarNumberonRoute(int routeNumber) {
		//测试完成
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where routeNumber=?");
			pstmt.setInt(1,routeNumber);
			ResultSet res=pstmt.executeQuery();
			
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getString(1));
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
	public static String getAllCarNumber() {
		//测试完成
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select carNumber from Car");
			boolean flag=true;
			while(res.next()){
				if(!flag){
					sb.append("#");
				}
				flag=false;
				sb.append(res.getString(1));
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
	public static String getLatestDeviceRecord(String read) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select TOP 1 * from Devicerecord where CarNumber=? order by Time desc");
			pstmt.setString(1,read);
			ResultSet res=pstmt.executeQuery();
			
			while(res.next()){
				sb.append(res.getString(1));
				sb.append("#");
				sb.append(res.getInt(4));
				sb.append("#");
				sb.append(res.getFloat(5));
				sb.append("#");
				sb.append(res.getInt(6));
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
