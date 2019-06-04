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
		Class.forName(JDBC_DRIVER);//注册驱动
		conn=DriverManager.getConnection(DB_URL,USER,PASS);//打开链接
		stmt=conn.createStatement();//执行查询
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
/*// JDBC 驱动名及数据库 URL
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }*/
}
