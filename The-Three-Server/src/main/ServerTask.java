package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerTask implements Runnable {

	private static int port=8080;
	private ServerSocket serverSocket;
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/The-Three-DB";
	private static String USER="mysql";
	private static String PASS="1604102";
	private static Statement stmt;
	private void initDB() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		stmt=null;
		Class.forName(JDBC_DRIVER);//ע������
		conn=DriverManager.getConnection(DB_URL,USER,PASS);//������
		stmt=conn.createStatement();//ִ�в�ѯ
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			initDB();
			
			serverSocket=new ServerSocket(port);
			Socket socket=serverSocket.accept();
			Server.newThread();
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			int op=input.readInt();
			switch(op) {
			case 1:output.writeChars(getCarInfo());output.flush();break;
			}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ResultSet query(String sql) throws SQLException {
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	private String getCarInfo() {
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		StringBuilder sb=new StringBuilder();
		
		
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
