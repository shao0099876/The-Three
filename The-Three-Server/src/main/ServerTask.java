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
/*// JDBC �����������ݿ� URL
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // �������
                System.out.print("ID: " + id);
                System.out.print(", վ������: " + name);
                System.out.print(", վ�� URL: " + url);
                System.out.print("\n");
            }
            // ��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }*/
}
