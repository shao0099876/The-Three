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

	public static String getDriverInfo(int peonum) {//查询某一驾驶员信息
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


	public static String getAllDriverInfo(){//查询所有驾驶员信息
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			System.out.println("AllDriverQuery_before");
			PreparedStatement pstmt=conn.prepareStatement("select * from Driver");
			
			ResultSet res=pstmt.executeQuery();
			System.out.println("AllDriverQuery_after");
			
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

	public static String addDriverInfo(String s){//增加驾驶员信息
		System.out.println("开始增加驾驶员信息");//测试
		String message="";//操作结果
		
		String[] data=new String[7];
		data=s.split("#");//获取信息
		
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
		int newpeopleNumber=Integer.valueOf(data[0]);
		System.out.println(newpeopleNumber);
		
		String newdriverNumber=data[1];
		System.out.println(newdriverNumber);
		
		String newpeopleName=data[2];
		System.out.println(newpeopleName);
		
		int newpeopleAge=Integer.valueOf(data[3]);		
		System.out.println(newpeopleAge);
		
		int newdriverAge=Integer.valueOf(data[4]);
		System.out.println(newdriverAge);
		
		String newphoNumber=data[5];
		System.out.println(newphoNumber);
		
		int newpeopleState=Integer.valueOf(data[6]);
		System.out.println(newpeopleState);
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select peopleNumber from Driver where peopleNumber = ?");
			pstmt.setInt(1,newpeopleNumber);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;//记录查询的个数
			
			while(res.next()){
				n=n+1;//有记录 就加一
			}
			
			if(n==0){//为空，说明无结果，为添加
				System.out.println("开始添加数据库");
				PreparedStatement pstmt1=conn.prepareStatement("insert into Driver values(?,?,?,?,?,?,?)");
				
				pstmt1.setInt(1,newpeopleNumber);
				pstmt1.setString(2,newdriverNumber);
				pstmt1.setString(3,newpeopleName);
				pstmt1.setInt(4,newpeopleAge);
				pstmt1.setInt(5,newdriverAge);
				pstmt1.setString(6,newphoNumber);
				pstmt1.setInt(7,newpeopleState);
				
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="添加信息成功";
				System.out.println("1");
			}
			if(n>0){//非空，不能添加
				message="存在驾驶员信息，不能添加";
				System.out.println("0");
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

	public static String modDriverInfo(String s){//修改驾驶员信息
		System.out.println("开始修改驾驶员信息");//测试
		String message="";//操作结果
		
		String[] data=new String[7];
		data=s.split("#");//获取信息
		
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
		int newpeopleNumber=Integer.valueOf(data[0]);
		System.out.println(newpeopleNumber);
		
		String newdriverNumber=data[1];
		System.out.println(newdriverNumber);
		
		String newpeopleName=data[2];
		System.out.println(newpeopleName);
		
		int newpeopleAge=Integer.valueOf(data[3]);		
		System.out.println(newpeopleAge);
		
		int newdriverAge=Integer.valueOf(data[4]);
		System.out.println(newdriverAge);
		
		String newphoNumber=data[5];
		System.out.println(newphoNumber);
		
		int newpeopleState=Integer.valueOf(data[6]);
		System.out.println(newpeopleState);
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select peopleNumber from Driver where peopleNumber = ?");
			pstmt.setInt(1,newpeopleNumber);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;//记录查询的个数
			
			while(res.next()){
				n=n+1;//有记录 就加一
			}
			
			if(n==0){//为空，说明无结果，无法修改
				message="不存在驾驶员信息，不能修改";
				System.out.println("0");
				
			}
			if(n>0){//非空，可以修改
				System.out.println("开始修改数据库");
				PreparedStatement pstmt1=conn.prepareStatement("update Driver set driverNumber = ? , peopleName = ? , peopleAge = ? , driverAge = ? , phoNumber = ? , peopleState = ? where peopleNumber = ?");
			
				pstmt1.setString(1,newdriverNumber);
				pstmt1.setString(2,newpeopleName);
				pstmt1.setInt(3,newpeopleAge);
				pstmt1.setInt(4,newdriverAge);
				pstmt1.setString(5,newphoNumber);
				pstmt1.setInt(6,newpeopleState);
				pstmt1.setInt(7,newpeopleNumber);
				
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="修改信息成功";
				System.out.println("1");
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

	public static String mohuDriverInfo(String s){//模糊查询驾驶员信息
		int peoplenum=Integer.valueOf(s);
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Driver where peopleNumber like ?");
			pstmt.setInt(1,peoplenum);
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

	public static String delDriverInfo(String s){//删除驾驶员信息
		System.out.println("开始删除驾驶员信息");//测试
		
		int deln=Integer.valueOf(s);//将驾驶员编号转化为INT型
		
		String message="";
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstmt=conn.prepareStatement("select peopleNumber from Driver where peopleNumber = ?");
			pstmt.setInt(1,deln);
			ResultSet res=pstmt.executeQuery();
			
			int n=0;
			while(res.next()){
				n=n+1;
			}
			
			if(n==0){//为空，说明无结果，没有办法进行删除操作
				message="没有该驾驶员信息，没有办法进行删除";
			}
			if(n>0){//非空，进行删除
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Driver where peopleNumber=?");
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
}
