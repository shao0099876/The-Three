package routeServer;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ServerTask;

public class Route_ServerTask extends ServerTask{
	public Route_ServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public static String getRouteInfo(int routnum) {//获取某条路线信息
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route where routeNumber=?");
			pstmt.setInt(1,routnum);
			ResultSet res=pstmt.executeQuery();
			
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
				sb.append(res.getString(4));
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

	public static String getAllRouteInfo() {//获取所有路线信息
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route");//查询所有路线信息
			ResultSet res=pstmt.executeQuery();
			
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
				sb.append(res.getString(4));
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

	public static String getMoHuRouteInfo(String delrouteinfo) {//获取某条路线信息
		// TODO Auto-generated method stub
		int routnum=Integer.valueOf(delrouteinfo);
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Route where routeNumber like ?");
			pstmt.setInt(1,routnum);
			ResultSet res=pstmt.executeQuery();
			
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
				sb.append(res.getString(4));
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

	public static String delRouteInfo(String del_routeinfo){//删除具体的某条路线信息
		System.out.println("开始删除路线信息");//测试
		int deln=Integer.valueOf(del_routeinfo);//将路线编号转化为INT型
		
		String message="";
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,deln);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//为空，说明无结果，没有办法进行删除操作
				message="没有该路线信息，没有办法进行删除";
			}
			else{//非空，进行删除
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Route where routeNumber=?");
					pstmt0.setInt(1,deln);
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

	public static String addOneRouteInfo(String s){//增加一条路线信息
		System.out.println("开始增加路线信息");//测试
		String message="";//操作结果
		
		String[] data=s.split("#");//获取信息
		
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		int newrouteNumber=Integer.valueOf(data[0]);
		System.out.println(newrouteNumber);
		
		String newstartAddr=data[1];
		System.out.println(newstartAddr);
		
		String newdestAddr=data[2];
		System.out.println(newdestAddr);
		
		String newmAddr=data[3];		
		System.out.println(newmAddr);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,newrouteNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//为空，说明无结果，为添加
				System.out.println("开始添加数据库");
				PreparedStatement pstmt1=conn.prepareStatement("insert into Route values(?,?,?,?)");
				
				pstmt1.setInt(1,newrouteNumber);
				pstmt1.setString(2,newstartAddr);
				pstmt1.setString(3,newdestAddr);
				pstmt1.setString(4,newmAddr);
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="添加信息成功";
			}
			else{//非空，不能添加
				message="存在路线信息，不能添加";
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

	public static String modOneRouteInfo(String s){//修改具体的路线信息
		System.out.println("开始修改路线信息");//测试
		String message="";//操作结果
		
		String[] data=s.split("#");//获取信息
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//数据类型转换
		int newrouteNumber=Integer.valueOf(data[0]);
		System.out.println(newrouteNumber);
		
		String newstartAddr=data[1];
		System.out.println(newstartAddr);
		
		String newdestAddr=data[2];
		System.out.println(newdestAddr);
		
		String newmAddr=data[3];		
		System.out.println(newmAddr);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select routeNumber from Route where routeNumber = ?");
			pstmt.setInt(1,newrouteNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//为空，说明无结果，不能添加
				message="不存在路线信息，不能修改";
			}
			else{//非空，可以修改
				System.out.println("开始修改数据库");
				PreparedStatement pstmt1=conn.prepareStatement("update Route set startAddr = ? , destAddr = ? , mAddr = ? where routeNumber = ?)");
				
				pstmt1.setString(1,newstartAddr);
				pstmt1.setString(2,newdestAddr);
				pstmt1.setString(3,newmAddr);
				pstmt1.setInt(4,newrouteNumber);
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="修改信息成功";
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
}
