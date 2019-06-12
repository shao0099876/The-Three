package stationServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ServerTask;

public class Station_ServerTask extends ServerTask{

	public Station_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getGPS(String stationName) {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select stationGPS from Station where stationName=?");
			pstmt.setString(1,stationName);
			ResultSet res=pstmt.executeQuery();

			while(res.next()){
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

	public static String addStation(String stationInfo) {
		// TODO Auto-generated method stub
		String message="";//操作结果
		
		String[] data=stationInfo.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		String stationName=data[0];
		String stationAddr=data[1];
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Station where stationName = ?");
			pstmt.setString(1,stationName);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，为添加
				System.out.println("开始添加数据库");
				PreparedStatement pstmt1=conn.prepareStatement("insert into Station values(?,?)");
				pstmt1.setString(1,stationName);
				pstmt1.setString(2,stationAddr);	
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="添加信息成功";
			}
			if(n>0){//非空，不能添加
				message="存在信息，不能添加";
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

	public static String delStation(String stationName) {
		// TODO Auto-generated method stub
		String message="";
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Station where stationName = ?");
			pstmt.setString(1,stationName);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，没有办法进行删除操作
				message="没有办法进行删除";
			}
			if(n>0){//非空，进行删除
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Station where stationName=?");
					pstmt0.setString(1,stationName);
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

	public static String modifyStation(String read) {
		// TODO Auto-generated method stub
		System.out.println("开始修改车辆信息");//测试
		String message="";//操作结果
		
		String[] data=read.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		String stationName=data[0];
		String stationAddr=data[1];
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Station where stationName = ?");
			pstmt.setString(1,stationName);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，无法修改
				message="不存在该车辆信息，不能修改";
			}
			if(n>0){//非空，有结果，可以进行修改
				System.out.println("开始更新数据库");
				//待修改
				PreparedStatement pstmt2=conn.prepareStatement("update Station set stationAddr = ? where stationName = ?");			
				pstmt2.setString(1,stationAddr);
				pstmt2.setString(2,stationName);
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

	public static String getStationList() {
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
			ResultSet res=stmt.executeQuery("select * from Station");
			boolean flag=true;
			while(res.next()) {
				if(!flag) {
					sb.append("#");
				}
				flag=false;
				sb.append(res.getString(1));
				sb.append("#");
				sb.append(res.getString(2));
				sb.append("#");
				sb.append(res.getString(3));
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

	public static String getMohuStationList(String s) {
		// TODO Auto-generated method stub
		s=s+"%";
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Staion where stationName like ?");
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

	public static void addStationRecord(String s) {
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
			ResultSet res=stmt.executeQuery("select COUNT(*) from StationRecord");
			recordNumber=res.getInt(1)+1;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据类型转换
		
		String carNumber=data[0];
		int routeNumber=Integer.valueOf(data[1]);
		String stationName=data[2];
		int type=Integer.valueOf(data[3]);
		
		SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=ft.format(new Date());
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into StationRecord values(?,?,?,?,?,?)");
			pstmt.setInt(1, recordNumber);
			pstmt.setString(2, carNumber);
			pstmt.setString(3, stationName);
			pstmt.setInt(4, type);
			pstmt.setString(5, time);
			pstmt.setInt(6, routeNumber);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
