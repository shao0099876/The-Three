package user.db;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import code.IO;
import entity.Car;
import entity.User;

public class User_Database {
	private static String addr="cal.srcserver.xyz";
	public static boolean login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "33");
			System.out.println(username+"#"+password);
			IO.write(output, username+"#"+password);
			
			String raw_string=IO.read(input);
			
			boolean res=Boolean.valueOf(raw_string);
			output.close();
			input.close();
			socket.close();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static User getUser(String username) {
		// TODO Auto-generated method stub
		try {
			Socket socket= new Socket(addr,8081);
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			IO.write(output, "34");
			IO.write(output, username);
			
			String raw_string=IO.read(input);
			String[] data=raw_string.split("#");
			User res=new User(data[0],data[1],data[2]);
			output.close();
			input.close();
			socket.close();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
