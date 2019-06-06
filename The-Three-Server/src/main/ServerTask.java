package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import iotools.IO;

public class ServerTask implements Runnable {

	private static int port=8081;
	private ServerSocket serverSocket;
	private static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static String DB_URL = "jdbc:sqlserver://localhost:1433;user=SA;password=SHAO0123ruo;";
	private static String DB_URL = "jdbc:sqlserver://cal.srcserver.xyz:1433;databaseName=TheThreeDB;user=SA;password=SHAO0123ruo;";
	private static Statement stmt;
	private static Connection conn=null;
	private void initDB() throws ClassNotFoundException, SQLException {
		stmt=null;
		Class.forName(JDBC_DRIVER);//ע������
		conn=DriverManager.getConnection(DB_URL);//������
		stmt=conn.createStatement();//ִ�в�ѯ
	}
	private ResultSet query(String sql) throws SQLException {
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				serverSocket=new ServerSocket(port);
				Socket socket=serverSocket.accept();
				//Server.newThread();
				DataInputStream input=new DataInputStream(socket.getInputStream());
				DataOutputStream output=new DataOutputStream(socket.getOutputStream());
				int op=IO.readInt(input);//���������
				System.out.print(op+"\n");
				switch(op) {
				case 1:
					IO.write(output, getCarInfo());
					break;
				case 2:
					int peonum=IO.readInt(input);//�����ʻԱ���
					IO.write(output, getDriverInfo(peonum));
					break;
				case 3:
					int routnum=IO.readInt(input);//����·�ߵı��
					IO.write(output, getRouteInfo(routnum));
					break;
				case 4:
					String s=IO.read(input);//���������ֳ��ƺ���Ϣ
					System.out.println(s);
					IO.write(output, getCarNum(s));
					break;
				case 5:
					String carinfo=IO.read(input);//��Ҫ�����޸ĵĳ�����Ϣ������
					System.out.println(carinfo);//����
					IO.write(output, AddCarInfo(carinfo));
					break;
				case 6:
					String delcarinfo=IO.read(input);//��Ҫ����ɾ���ĳ�����Ϣ������
					System.out.println(delcarinfo);//����
					IO.write(output, DelCarInfo(delcarinfo));
					break;
				case 7:
					String modcarinfo=IO.read(input);//��Ҫ����ɾ���ĳ�����Ϣ������
					System.out.println(modcarinfo);//����
					IO.write(output, ModCarInfo(modcarinfo));
					break;
				}
				
				stmt.close();//�ر����ݿ�
				input.close();
				output.close();
				socket.close();//�ر�����
				serverSocket.close();//�ر�����
				
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getRouteInfo(int routnum) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	
	private String getDriverInfo(int peonum) {//��ѯ��ʻԱ��Ϣ
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from Driver where peopleNumber=?");
			pstmt.setInt(1,peonum);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private String getCarInfo() {
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			ResultSet res=query("select * from Car");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	private String getCarNum(String s){//��ѯ�����ĳ��ƺţ�ģ����ѯ��
		System.out.print("�����ݿ�");
		s=s+"%";//ʵ��ģ����ѯ
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder sb=new StringBuilder();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber like ?");
			pstmt.setString(1,s);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}


	private String AddCarInfo(String s){//�������ӳ�����Ϣ
		System.out.println("��ʼ�޸ĳ�����Ϣ");//����
		String message="";//�������
		
		String[] data=s.split("#");//��ȡ��Ϣ
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��������ת��
		String newcarNumber=data[0];
		int newpeople1Number=Integer.valueOf(data[1]);
		int newpeople2Number=Integer.valueOf(data[2]);
		int newrouteNumber=Integer.valueOf(data[3]);
		System.out.println(newcarNumber);
		System.out.println(newpeople1Number);
		System.out.println(newpeople2Number);
		System.out.println(newrouteNumber);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber = ?");
			pstmt.setString(1,newcarNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//Ϊ�գ�˵���޽����Ϊ���
				System.out.println("��ʼ������ݿ�");
				PreparedStatement pstmt1=conn.prepareStatement("insert into Car values(?,?,?,?)");
				pstmt1.setString(1,newcarNumber);
				pstmt1.setInt(2,newpeople1Number);
				pstmt1.setInt(3,newpeople2Number);
				pstmt1.setInt(4,newrouteNumber);	
				pstmt1.executeUpdate();  
				pstmt1.close();
				message="�����Ϣ�ɹ�";
			}
			else{//�ǿգ��������
				message="���ڳ�����Ϣ���������";
			}
			res.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	private String DelCarInfo(String s){//����ɾ��������Ϣ
		System.out.println("��ʼɾ��������Ϣ");//����
		String[] data=s.split("#");//��ȡ��Ϣ
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
			
			if(res==null){//Ϊ�գ�˵���޽����û�а취����ɾ������
				message="û�иó�����Ϣ��û�а취����ɾ��";
			}
			else{//�ǿգ�����ɾ��
				try {
					PreparedStatement pstmt0 = conn.prepareStatement("delete from Car where carNumber=?");
					pstmt0.setString(1,data[0]);
					pstmt0.executeUpdate();  
					pstmt0.close();
					message="ɾ���ɹ�";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			res.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	private String ModCarInfo(String s){//�����޸ĳ�����Ϣ
		System.out.println("��ʼ�޸ĳ�����Ϣ");//����
		String message="";//�������
		
		String[] data=s.split("#");//��ȡ��Ϣ
		
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��������ת��
		String newcarNumber=data[0];
		int newpeople1Number=Integer.valueOf(data[1]);
		int newpeople2Number=Integer.valueOf(data[2]);
		int newrouteNumber=Integer.valueOf(data[3]);
		System.out.println(newcarNumber);
		System.out.println(newpeople1Number);
		System.out.println(newpeople2Number);
		System.out.println(newrouteNumber);
		
			
		try {
			PreparedStatement pstmt=conn.prepareStatement("select carNumber from Car where carNumber = ?");
			pstmt.setString(1,newcarNumber);
			ResultSet res=pstmt.executeQuery();
			
			if(res==null){//Ϊ�գ�˵���޽�����޷��޸�
				message="�����ڸó�����Ϣ�������޸�";
			}
			else{//�ǿգ��н�������Խ����޸�
				System.out.println("��ʼ�������ݿ�");
				//���޸�
				PreparedStatement pstmt2=conn.prepareStatement("update Car set people1Number = ? people2Number = ? routeNumber = ? where carNumber = ?");			
				pstmt2.setInt(1,newpeople1Number);
				pstmt2.setInt(2,newpeople2Number);
				pstmt2.setInt(3,newrouteNumber);	
				pstmt2.setString(4,newcarNumber);
				pstmt2.executeUpdate();
				pstmt2.close();
				message="�޸ĳɹ�";
			}
			res.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
