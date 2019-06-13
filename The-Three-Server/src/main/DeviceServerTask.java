package main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import iotools.IO;

public class DeviceServerTask extends ServerTask implements Runnable {
	public DeviceServerTask(Socket p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			initDB();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DataInputStream input = null;
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String raw_string=IO.read(input);
		DebugInfo.DebugInfo(raw_string);
		String[] array=raw_string.split("#");
		float fuel=Float.valueOf(array[2]);
		int driver=Integer.valueOf(array[3]);
		int status=Integer.valueOf(array[4]);
		int subdriver=Integer.valueOf(array[5]);
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("insert into Devicerecord values(?,?,?,?,?,?,?)");
			pstmt.setString(1,array[0]);
			pstmt.setString(2,array[1]);
			Date now=new Date();
			SimpleDateFormat ft=new SimpleDateFormat("yyyyMMddhhmmss");//20190522101121
			pstmt.setString(3, ft.format(now));
			pstmt.setInt(4, driver);
			pstmt.setFloat(5, fuel);
			pstmt.setInt(6, status);
			pstmt.setInt(7, subdriver);
			pstmt.executeUpdate();  
			pstmt.close();
			
			pstmt=conn.prepareStatement("update Car set people1Number=?,people2Number=? where carNumber =?");
			pstmt.setInt(1, driver);
			pstmt.setInt(2, subdriver);
			pstmt.setString(3, array[0]);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
